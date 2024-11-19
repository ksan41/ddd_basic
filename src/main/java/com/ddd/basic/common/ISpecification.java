package com.ddd.basic.common;

public interface ISpecification<T> {
    boolean isSatisfied(T value);
}
