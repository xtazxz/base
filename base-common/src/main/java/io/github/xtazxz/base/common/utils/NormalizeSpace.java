package io.github.xtazxz.base.common.utils;

public interface NormalizeSpace {

  default String normalizeSpace(final String arg) {
    if (arg == null) {
      return null;
    }
    return arg.replace("\r\n", "_")
        .replace("\r", "_")
        .replace("\n", "_");
  }

  default Object normalizeSpace(final Object arg) {
    if (arg == null) {
      return null;
    }
    return arg.toString().replace("\r\n", " ")
        .replace("\r", " ")
        .replace("\n", " ");
  }

  default Object[] normalizeSpace(final Object[] arguments) {
    if (arguments == null || arguments.length == 0) {
      return arguments;
    }
    Object[] newArguments = new Object[arguments.length];
    for (int i = 0; i < arguments.length; i++) {
      newArguments[i] = normalizeSpace(arguments[i]);
    }
    return newArguments;
  }

}
