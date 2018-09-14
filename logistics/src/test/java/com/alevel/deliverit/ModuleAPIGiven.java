package com.alevel.deliverit;

/**
 * @author Vadym Mitin
 */
public class ModuleAPIGiven {

    public static class TestClass1 implements BusinessLogicService {
        @Subscribe("value 1")
        public String stringMethod() {
            return "Some Method 1 return";
        }

        @Subscribe("value 2")
        public int intMethod() {
            return 10;
        }

        @Subscribe("value 3")
        public Object nullMessageMethod() {
            return null;
        }

        @Subscribe("value 4")
        public void voidMessageMethod() {
            System.out.println("void message");
        }
    }

    public static class TestClass2 implements BusinessLogicService {
        @Subscribe(value = "Str")
        public String stringMethod(String s) {
            return s;
        }

        @Subscribe(value = "Int")
        public int intMethod(int i) {
            return i;
        }

        @Subscribe(value = "Bool")
        public boolean boolMethod(boolean b) {
            return b;
        }
    }

    public static class TestClass3 implements BusinessLogicService {
        @Subscribe(value = "address.String")
        public String stringMethod(String s) {
            return "Some Method 1 return" + s;
        }

        @Subscribe(value = "address.String")
        public double doubleMethod(double s) {
            return 15;
        }


    }

    public static class TestClass4 implements BusinessLogicService {
        private static int i = 0;

        public static int getI() {
            return i;
        }

        @Subscribe("address.1")
        public void stringMethod(String s) {
            System.out.println("address.1 = " + i);
            i++;
            System.out.println(s);
        }

        @Subscribe("address.2")
        public void doubleMethod(Double s) {
            System.out.println("address.2 = " + i);
            i++;
            System.out.println(s);
        }
    }

    public static class TestClass5 implements BusinessLogicService {
        private static int i = 0;

        public static int getI() {
            return i;
        }

        @Subscribe("address.3")
        public void stringMethod(String s) {
            System.out.println("address.3 = " + i);
            i++;
            System.out.println(s);
        }

        @Subscribe("address.4")
        public void doubleMethod(Double s) {
            System.out.println("address.4 = " + i);
            i++;
            System.out.println(s);
        }
    }
}
