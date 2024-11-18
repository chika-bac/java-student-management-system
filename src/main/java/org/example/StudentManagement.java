package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

  Scanner scanner = new Scanner(System.in);
  List<Student> studentList = new ArrayList<>();

  //  1. 学生を追加
  public void addStudent() {
    System.out.print("学生の名前を入力してください: ");
    String studentName = scanner.next();

    int studentScore = 0;
    boolean isValidInput = false;
    while (!isValidInput) {
      try {
        System.out.print(studentName + "の点数を入力してください:");
        studentScore = scanner.nextInt();
        isValidInput = true;
      } catch (InputMismatchException e) {
        System.out.println("不正な入力です。点数を入力してください。");
        scanner.next();
      }
    }

    studentList.add(new Student(studentName, studentScore));
  }

  //  2. 学生を削除
  public void deleteStudent() {
    System.out.print("削除する学生の名前を入力してください: ");
    String studentName = scanner.next();

//    該当する学生がリストに存在したら削除
    boolean isRemoved = studentList.removeIf(student -> student.getName().equals(studentName));

    if (isRemoved) {
      System.out.println(studentName + "を削除しました。");
    } else {
      System.out.println("該当する学生は見つかりませんでした。");
    }
  }

  //  3. 点数を更新
  public void updateScore() {
    System.out.print("点数を更新する生徒名を入力してください: ");
    String studentName = scanner.next();

//    該当する学生を抽出（見つからなければnullを格納）
    Student targetStudent = studentList.stream()
        .filter(student -> student.getName().equals(studentName))
        .findFirst()
        .orElse(null);

    if (targetStudent != null) {
//  名前と点数を表示
      System.out.println("名前: " + targetStudent.getName());
      System.out.println("点数: " + targetStudent.getScore());

      int newScore = 0;
      boolean isValidInput = false;
      while (!isValidInput) {
        try {
          System.out.print("点数を入力してください: ");
          newScore = scanner.nextInt();

//          正しい入力時はループを抜ける
          isValidInput = true;
        } catch (InputMismatchException e) {
          System.out.println("不正な入力です。点数を入力してください。");
          scanner.next();
        }
      }

//      点数を更新
      targetStudent.setScore(newScore);
      System.out.println("更新後の点数: " + targetStudent.getScore());
    } else {
      System.out.println("該当する学生が見つかりませんでした。");
    }
  }

  //  4. 平均点を計算
  public void calcAverage() {
    if (!studentList.isEmpty()) {
      double totalScore = 0;
      for (Student student : studentList) {
        totalScore += student.getScore();
      }
      System.out.println("平均点: " + totalScore / studentList.size() + "点");
    } else {
      System.out.println("学生が登録されていません。");
    }

  }

  //  5. 全学生の情報を開示
  public void printAllStudents() {
    if (!studentList.isEmpty()) {
      System.out.println("学生一覧:");
      for (Student student : studentList) {
        System.out.println(student.getName() + ": " + student.getScore() + "点");
      }
    } else {
      System.out.println("学生が登録されていません。");
    }
  }
}