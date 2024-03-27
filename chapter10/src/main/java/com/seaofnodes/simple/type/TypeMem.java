package com.seaofnodes.simple.type;

import java.util.Objects;

/**
 * Represents a slice of memory corresponding to an alias
 */
public class TypeMem extends Type {

    private final AliasSource _aliasSource;

    public TypeMem(AliasSource aliasSource) {
        super(TMEM);
        this._aliasSource = aliasSource;
    }

    @Override
    protected Type xmeet(Type t) {
        TypeMem other = (TypeMem) t;
        if (other._aliasSource.alias() == _aliasSource.alias()) return this;
        else throw new RuntimeException("Unexpected meet between Mem types");
    }

    @Override
    int hash() {
        return Objects.hash(_type, _aliasSource.alias());
    }

    @Override
    boolean eq(Type t) {
        if (t instanceof TypeMem ts)
            return ts._aliasSource.alias() == _aliasSource.alias();
        return false;
    }

    @Override
    public StringBuilder _print(StringBuilder sb) { return sb.append("Mem#").append(_aliasSource.alias()); }


    // We set 2 as the ALLMEM alias because it can then occupy 2 slot in START
    // and field aliases start from 3.
    public static final AliasSource ALLMEM_ALIAS = new AliasSource() {
        @Override
        public int alias() { return 2; }

        @Override
        public String aliasName() { return "$mem"; }
    };

    public static final TypeMem ALLMEM = new TypeMem(ALLMEM_ALIAS).intern();
}
