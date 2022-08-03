package me.jumper251.replay.reflect;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

//Removed in Protocollib v5.0.0-SNAPSHOT
public class IntEnum {
    protected BiMap<Integer, String> members = HashBiMap.create();

    public IntEnum() {
        this.registerAll();
    }

    protected void registerAll() {
        try {
            Field[] var1 = this.getClass().getFields();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Field entry = var1[var3];
                if (entry.getType().equals(Integer.TYPE)) {
                    this.registerMember(entry.getInt(this), entry.getName());
                }
            }
        } catch (IllegalArgumentException var5) {
            var5.printStackTrace();
        } catch (IllegalAccessException var6) {
            var6.printStackTrace();
        }

    }

    protected void registerMember(int id, String name) {
        this.members.put(id, name);
    }

    public boolean hasMember(int id) {
        return this.members.containsKey(id);
    }

    public Integer valueOf(String name) {
        return (Integer)this.members.inverse().get(name);
    }

    public String getDeclaredName(Integer id) {
        return (String)this.members.get(id);
    }

    public Set<Integer> values() {
        return new HashSet(this.members.keySet());
    }
}
