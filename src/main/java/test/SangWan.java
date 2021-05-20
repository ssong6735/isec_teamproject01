package main.java.test;

import java.util.Scanner;

public class SangWan {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int i = 0;//책 수량 카운트 변수

        String[][] books = new String[1000][5];//데이터베이스 2차배열
        int listLength = 0;

        boolean end = false; //프로그램 종료
        System.out.println(books.length);
        System.out.println(books[0].length);

        while (true) {
            System.out.println("=====도서관리 프로그램=====");
            System.out.println("# 1. 도서 신규 등록");
            System.out.println("# 2. 모든 도서정보 보기");
            System.out.println("# 3. 도서정보 검색");
            System.out.println("# 4. 도서정보 수정");
            System.out.println("# 5. 도서정보 삭제");
            System.out.println("# 6. 프로그램 종료");
            System.out.println("============================");
            System.out.print("메뉴 입력: ");
            int menuInsert = sc.nextInt();

            switch (menuInsert) {
                case 1:
                    System.out.println("# 신규도서 등록을 시작합니다.");

                    String[] temp = new String[5];
                    System.out.print("- 책 번호: ");
                    temp[0] = sc.next();
                    System.out.print("- 책 제목: ");
                    temp[1] = sc.next();
                    System.out.print("- 저자: ");
                    temp[2] = sc.next();
                    System.out.print("- 출판사: ");
                    temp[3] = sc.next();
                    System.out.print("- 가격: ");
                    temp[4] = sc.next();
                    // 1차원 배열 정보 입력

                    books[i] = temp;
                    temp = null;
                    i++;
                    listLength++;
                    System.out.println(listLength);
                    break;
                case 2:
                    for (String[] book : books) {
                        System.out.println("===============================도 서 정 보 ============================================================");
                        System.out.println("번호 \t\t 제목 \t\t     저자 \t\t       출판사\t\t   가격 \t\t     선호도");
                        System.out.println("=======================================================================================================");
                        for (String bookSearch : book) {
                            System.out.print(bookSearch + "\t\t");
                        }
                    }

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("# 삭제하실 책 번호를 입력하세요.");
                    System.out.print("> ");
                    String delNum = sc.next();//지울 책 번호
                    boolean hasBook = false;//책 검색 결과 변수

                    for (int j = 0; j < listLength; j++) {
                        if (books[j][0].equals(delNum)) {
                            hasBook = true;//해당 도서를 찾으면 논리값을 true로 변환
                            i--;
                            listLength--;


                        }
                    }
                    if (!hasBook) {
                        //해당 도서를 찾았다면 논리값이 true로 바뀌므로 여전히 false라면 찾지 못한 것이므로
                        //아래와 같이 출력함.
                        System.out.println("못찾음");
                    }

                    break;
                case 6:
                    end = true;
                    break;
                default:
                    System.out.println("메뉴를 잘못 입력했습니다.");
                    continue;

            }

        }


    }
}