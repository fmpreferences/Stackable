package com.lonkachu;

import net.minecraft.util.Identifier;

public class StacksizeOverride {
    private String namespaceID;
    private int value;
    public StacksizeOverride(String namespaceID, int value)
    {
        this.namespaceID = namespaceID;
        this.value = value;
    }

    Identifier GetIdentifier() { return Identifier.of(namespaceID); }
    public int GetCount() { return value; }
}
