package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StudentManagement studentManagement = new StudentManagement();

    boolean isValidInput = false;
    while (!isValidInput) {
      try {
        System.out.println("""
            1. 学生を追加
            2. 学生を削除
            3. 点数を更新
            4. 平均点を計算
            5. 全学生の情報を表示
            6. 終了
            選択してください: 
            """);

        int selectedNumber = scanner.nextInt();

        switch (selectedNumber) {
          case 1 -> studentManagement.addStudent();
          case 2 -> studentManagement.deleteStudent();
          case 3 -> studentManagement.updateScore();
          case 4 -> studentManagement.calcAverage();
          case 5 -> studentManagement.printAllStudents();
          case 6 -> {
            System.out.println("プログラムを終了します。");
            isValidInput = true;
          }
          default -> {
            System.out.println("1〜6から選択してください。");
          }
        }
      } catch (InputMismatchException e) {
        System.out.println("不正な入力です。1〜6から選択してください。");
        scanner.next();
      }
    }
  }
}