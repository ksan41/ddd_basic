package com.ddd.basic.domain.shared;

public interface ISpecification<T> {
    boolean isSatisfied(T value);
}
