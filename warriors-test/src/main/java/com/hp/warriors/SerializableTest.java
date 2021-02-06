package com.hp.warriors;

import com.hp.warriors.entity.seattle.SerializableEntity;

import java.io.*;

public class SerializableTest {

    /**
     * 将User对象作为文本写入磁盘
     */
    public static void writeObj() {
        SerializableEntity entity = new SerializableEntity("1001", "Joe");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("/Users/heping/user.txt"));
            objectOutputStream.writeObject(entity);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readObj() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/Users/heping/user.txt"));
            try {
                Object object = objectInputStream.readObject();
                SerializableEntity entity = (SerializableEntity) object;
                System.out.println(entity);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
//        writeObj();
        readObj();
    }
}
