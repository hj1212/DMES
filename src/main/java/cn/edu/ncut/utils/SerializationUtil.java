package cn.edu.ncut.utils;

import java.io.*;

/**
 * @author NikoBelic
 * @create 21/01/2017 17:20
 */
public class SerializationUtil
{
    /**
     * 序列化
     * @param object
     * @return
     */
    public static <T extends Serializable> byte[] serialize(T object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static <T extends Serializable> T  deserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try
        {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}