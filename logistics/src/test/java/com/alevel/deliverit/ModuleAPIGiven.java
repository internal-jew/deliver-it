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
        public void stringMethod(Object s) {
            i++;
            String t = (String) s;
            System.out.println(t);
//            return "Some Method 1 return" + s;
        }

        @Subscribe("address.2")
        public void doubleMethod(Object s) {
            i++;
            double t = (double) s;
            System.out.println(t);
        }
    }
}
