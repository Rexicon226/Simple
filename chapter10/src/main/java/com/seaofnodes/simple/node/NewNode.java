package com.seaofnodes.simple.node;

import com.seaofnodes.simple.Parser;
import com.seaofnodes.simple.type.Type;
import com.seaofnodes.simple.type.TypeMemPtr;
import com.seaofnodes.simple.type.TypeTuple;

import java.util.BitSet;

public class NewNode extends MultiNode {

    TypeMemPtr _ptr;

    public NewNode(TypeMemPtr ptr, Node ctrl, Node mem) {
        super(ctrl, mem);
        this._ptr = ptr;
    }

    public TypeMemPtr ptr() { return _ptr; }

    @Override
    public String label() {
        return "New";
    }

    @Override
    public String glabel() {
        return "New " + ptr();
    }

    @Override
    StringBuilder _print1(StringBuilder sb, BitSet visited) {
        return sb.append("new ").append(_ptr._print(sb));
    }

    @Override
    public Type compute() {
        return TypeTuple.make(new Type[] { Type.XCONTROL, in(1)._type, _ptr });
    }

    @Override
    public Node idealize() {
        return null;
    }

    @Override
    boolean eq(Node n) { return false; }

    @Override
    int hash() { return System.identityHashCode(this); }
}
