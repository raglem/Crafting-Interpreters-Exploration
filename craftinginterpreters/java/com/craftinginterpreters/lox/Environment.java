//> Statements and State environment-class
package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;

class Environment {
//> enclosing-field
  final Environment enclosing;
//< enclosing-field
  private final List<Object> values = new ArrayList<Object>();
//> environment-constructors
  Environment() {
    enclosing = null;
  }

  Environment(Environment enclosing) {
    this.enclosing = enclosing;
  }
//< environment-constructors

//> environment-define
  void define(String name, Object value) {
    values.add(value);
  }
//< environment-define
//> Resolving and Binding ancestor
  Environment ancestor(int distance) {
    Environment environment = this;
    for (int i = 0; i < distance; i++) {
      environment = environment.enclosing; // [coupled]
    }

    return environment;
  }
//< Resolving and Binding ancestor
//> Resolving and Binding get-at
  Object getAt(int distance, int slot) {
    Environment environment = this;

    // Go up scopes
    for (int i = 0; i < distance; i++) {
      environment = environment.enclosing;
    }

    return environment.values.get(slot);
  }
//< Resolving and Binding get-at
//> Resolving and Binding assign-at
  void assignAt(int distance, int slot, Object value) {
    Environment environment = this;

    // Go up scopes
    for (int i = 0; i < distance; i++) {
      environment = environment.enclosing;
    }

    environment.values.set(slot, value);
  }
//< Resolving and Binding assign-at
//> omit
  @Override
  public String toString() {
    String result = values.toString();
    if (enclosing != null) {
      result += " -> " + enclosing.toString();
    }

    return result;
  }
//< omit
}
