package it.unical.mat.embasp.languages;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.IllegalTermException;
import it.unical.mat.embasp.languages.asp.SymbolicConstant;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class
 * Contains methods used to transform Objects into {@link InputProgram}
 */

public abstract class Mapper {

  protected final Map<String, Class<?>> predicateClass = new HashMap<>();

  protected final Map<Class<?>, Map<String, Method>> classSetterMethod = new HashMap<>();

  protected abstract String getActualString(String predicate, HashMap<Integer, Object> parametersMap) throws IllegalTermException;

  public Class<?> getClass(final String predicate) {
    return predicateClass.get(predicate);
  }

  /**
   * Returns an Object for the given string
   *
   * @param atom String from witch data are extrapolated
   * @return Object for the given String data
   * @throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IllegalTermException
   */
  public Object getObject(final String atom) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
      SecurityException, InstantiationException {
    final String predicate = getId(atom);

    if (predicate == null)
      return null;

    final Class<?> cl = getClass(predicate);

    if (cl == null)
      return null;

    final String[] parameters = getParam(atom);

    if (parameters == null)
      return null;

    final Object object = cl.getDeclaredConstructor().newInstance();

    populateObject(cl, parameters, object);

    return object;
  }

  /**
   * @return The predicate name
   */
  protected abstract String getId(final String atom);

  /**
   * @return All the Terms
   */
  protected abstract String[] getParam(final String atom);

  /**
   * Returns data for the given Object
   *
   * @param obj Object from witch data are extrapolated
   * @return String data for the given Object in a String format
   * @throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IllegalTermException, IllegalTermException
   */
  public String getString(final @NotNull Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
      SecurityException, ObjectNotValidException, IllegalAnnotationException, IllegalTermException {
    final String predicate = registerClass(obj.getClass());

    final HashMap<Integer, Object> parametersMap = new HashMap<>();
    for (final Field field : obj.getClass().getDeclaredFields()) {
      if (field.isSynthetic())
        continue;
      if (field.isAnnotationPresent(Param.class)) {
        final Object value = obj.getClass().getMethod("get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1))
            .invoke(obj);
        parametersMap.put(field.getAnnotation(Param.class).value(), value);
      }
    }
    return getActualString(predicate, parametersMap);
  }

  private void populateObject(final @NotNull Class<?> cl, final String[] parameters, final Object obj) throws IllegalAccessException, InvocationTargetException {
    for (final Field field : cl.getDeclaredFields())
      if (field.isAnnotationPresent(Param.class)) {

        final int term = field.getAnnotation(Param.class).value();
        final String nameMethod = "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
        final Method method = classSetterMethod.get(cl).get(nameMethod);

        if (method.getParameterTypes()[0].getName().equals(int.class.getName())
            || method.getParameterTypes()[0].getName().equals(Integer.class.getName()))
          method.invoke(obj, Integer.valueOf(parameters[term]));
        else if (method.getParameterTypes()[0].getName().equals(SymbolicConstant.class.getName()))
          method.invoke(obj, new SymbolicConstant(parameters[term]));
        else
          method.invoke(obj, parameters[term]);

      }
  }

  /**
   * insert an object into {@link #predicateClass} and {@link #classSetterMethod}
   *
   * @return String representing pairing key of {@link #predicateClass}
   */
  public String registerClass(final @NotNull Class<?> cl) throws ObjectNotValidException, IllegalAnnotationException {

    final Id annotation = cl.getAnnotation(Id.class);

    if (annotation == null)
      throw new IllegalAnnotationException();

    final String predicate = annotation.value();

    if (predicate.contains(" "))
      throw new ObjectNotValidException();

    // String predicate = cl.getAnnotation(Predicate.class).value();
    predicateClass.put(predicate, cl);
    final Map<String, Method> namesMethods = new HashMap<>();
    for (final Method method : cl.getMethods())
      if (method.getName().startsWith("set"))
        namesMethods.put(method.getName(), method);
    classSetterMethod.put(cl, namesMethods);
    return predicate;
  }

  public void unregisterClass(final @NotNull Class<?> cl) throws IllegalAnnotationException {

    final Id annotation = cl.getAnnotation(Id.class);

    if (annotation == null)
      throw new IllegalAnnotationException();

    final String predicate = annotation.value();

    predicateClass.remove(predicate);
    classSetterMethod.remove(cl);

  }

}
