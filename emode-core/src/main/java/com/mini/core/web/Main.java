package com.mini.core.web;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    static class Pet {

        public Pet() {
        }

        public Pet(String name) {
            this.name = name;
        }

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }


    public static void main(String[] args) {

        Pet petA = new Pet("汤姆");

        Pet petB = new Pet();

        String name = petA.getName();


        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            petB.setName(name);
        }
        long end = System.currentTimeMillis();

        System.out.println("共计耗时：" + (end - start));
    }
}