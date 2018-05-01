package jvm;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClassAnalysis {

    // 保存分析的每一个常量
    private static CpStruct[] cpStructs = null;

    public static void main(String[] args) throws Exception {
        DataInputStream dis = readFile("tech-interview/target/classes/jvm/ExampleClass.class");
        // 打印魔数，次版本，主版本
        pnMaJor(dis);
        // 分析常量池
        analyCp(dis);
        // 打印字段、方法，超类，实现的接口
        pnThisClass(dis);
        pnFeilds(dis);
        pnMethods(dis);

    }

    private static void pnMethods(DataInputStream dis) throws IOException {
        pn("方法列表");
        int fc = dis.readShort();
        for (int i = 0; i < fc; i++) {
            Integer accessFlags = Integer.valueOf(dis.readShort());
            int nameIndex = dis.readShort();
            int descIindex = dis.readShort();
            pn("accessFlags:" + Integer.toHexString(accessFlags) + "| desc:" + cpStructs[descIindex].getContent()
                    + " |fname :" + cpStructs[nameIndex].getContent());
            // 读取方法的个数
            int attc=dis.readShort();
            for (int j = 0; j < attc; j++) {
                int anIndex = dis.readShort();//指向常量池的索引，代表是code
                int aLength = dis.readInt();//文件内容长度
                byte[] bytes = new byte[aLength];
                //忽略文件内容
                dis.read(bytes);
            }
        }
    }

    private static void pnFeilds(DataInputStream dis) throws IOException {
        pn("字段列表");
        int fc = dis.readShort();
        for (int i = 0; i < fc; i++) {
            Integer accessFlags = Integer.valueOf(dis.readShort());
            int nameIndex = dis.readShort();//字段名的常量池索引
            int descIindex = dis.readShort();//方法的修饰符
            pn("accessFlags:" + Integer.toHexString(accessFlags) + "| desc:" + cpStructs[descIindex].getContent()
                    + " |fname :" + cpStructs[nameIndex].getContent());
            // 由于这里的class没有attribute,没有去写代码追踪
            dis.readShort();
        }
    }

    private static void pnThisClass(DataInputStream dis) throws IOException {
        pn("class信息");
        Integer accessFlags = Integer.valueOf(dis.readShort());
        int thisClassIndex = dis.readShort();
        int superIindex = dis.readShort();
        int superInterCount = dis.readShort();
        // 通过index找到常量池位置 ，再通过引用找到字面值
        // 由于没有实现接口，这里就没有在去写代码追踪
        pn("access flags:" + Integer.toHexString(accessFlags) + " | this class:" + getClassName(thisClassIndex)
                + " | super class:" + getClassName(superIindex) + "| interface:" + superInterCount);
    }

    private static String getClassName(int index) {
        return cpStructs[cpStructs[index].refIndex1].getContent();
    }

    private static void analyCp(DataInputStream dis) throws IOException {
        p("cp count: ");
        // 读取常量个数
        int count = dis.readShort();
        cpStructs = new CpStruct[count];
        pn(count);
        for (int i = 1; i < count; i++) {
            int flag = dis.readByte();
            CpStruct cps = new CpStruct();
            cps.tag = flag;
            cpStructs[i] = cps;
            switch (flag) {
                case 7:
                    oneIndex(dis, i, cps);
                    break;
                case 1:
                    utf8Info(dis, i, cps);
                    break;

                case 9:
                case 11:
                case 10:
                case 12:
                    twoIndex(dis, i, cps);
                    break;
                default:
                    pn("unknow flag:" + flag);
                    break;
            }
        }
    }

    // 保存字段、方法，超类，实现引用关系
    private static void twoIndex(DataInputStream dis, int i, CpStruct cps) throws IOException {
        int refIndex1 = dis.readShort();
        int refIndex2 = dis.readShort();
        cps.refIndex1 = refIndex1;
        cps.refIndex2 = refIndex2;

    }

    // 保存常量值信息
    private static void utf8Info(DataInputStream dis, int i, CpStruct cps) throws IOException {
        int length = dis.readShort();
        byte[] bytes = new byte[length];
        dis.read(bytes);
        cps.length = length;
        cps.bytes = bytes;
        // 打印常量
        // pn(new String(bytes));
    }

    private static void oneIndex(DataInputStream dis, int i, CpStruct cps) throws IOException {
        int refIndex1 = dis.readShort();
        cps.refIndex1 = refIndex1;
    }

    private static void pnMaJor(DataInputStream dis) throws IOException {
        Integer magic = dis.readInt();
        p("magic: 0x");
        pn(Integer.toHexString(magic).toUpperCase());
        p("minor: ");
        pn(dis.readShort());
        p("major: ");
        pn(dis.readShort());
    }

    private static DataInputStream readFile(String string) throws FileNotFoundException {
        DataInputStream dis = new DataInputStream(new FileInputStream(string));
        return dis;
    }

    public static void pn(Object o) {
        System.out.println(o);
    }

    public static void p(Object o) {
        System.out.print(o);
    }
}

// 保存读取的一个cp结构
class CpStruct {
    int tag;
    int refIndex1;
    int refIndex2;
    int length;
    byte[] bytes;

    String getContent() {
        return new String(bytes);
    }
}

