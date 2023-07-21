package it.unical.mat.parsers.asp;

public interface ASPDataCollection {
  void addAnswerSet();

  void storeAtom(final String atom);

  void storeCost(final int level, final int weight);
}
