package com.helper.work.courses.javafund;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class MyClass implements Comparable<MyClass> {
    private String label, value;

    @Override
    public String toString() {
        return  "label= " + label  +
                ", value= " + value ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return Objects.equals(label, myClass.label) && Objects.equals(value, myClass.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, value);
    }

    /**
     * it returns a negative (-) if this is less than Object
     * it returns a zero (0) if this is equals than Object
     * it returns a positive (+) if this is greater than Object
     * @param o
     * @return
     */
    @Override
    public int compareTo(MyClass o) {
        return this.label.compareToIgnoreCase(o.label) + this.value.compareToIgnoreCase(o.value);
    }
}
