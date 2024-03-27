package com.seaofnodes.simple.type;

import java.util.Objects;

/**
 * Represents a field in a struct
 */
public class TypeField implements AliasSource {

    /**
     * Alias ID generator - we start at 2 because START uses 0-2 slots,
     * by starting at 3, our alias ID is nicely mapped to a slot in Start.
     */
    static final int _RESET_ALIAS_ID = 3;
    static int _ALIAS_ID = _RESET_ALIAS_ID;

    /**
     * Struct type that owns this field
     */
    final TypeStruct _structType;
    /**
     * Type of the field, for now can only be int
     */
    public final Type _fieldType;
    /**
     * Field name
     */
    public final String _fieldName;

    public final int _alias;

    public TypeField(TypeStruct _structType, Type _fieldType, String _fieldName) {
        this._structType = _structType;
        this._fieldType = _fieldType;
        this._fieldName = _fieldName;
        this._alias = _ALIAS_ID++;
    }

    @Override
    public String aliasName() { return "$" + _structType._name + "_" + _fieldName; }

    @Override
    public int alias() { return _alias; }

    public static void reset() { _ALIAS_ID = _RESET_ALIAS_ID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeField typeField = (TypeField) o;
        return Objects.equals(_structType, typeField._structType) && Objects.equals(_fieldName, typeField._fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_structType, _fieldName);
    }
}
