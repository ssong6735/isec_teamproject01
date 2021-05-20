package main.java.test;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        /*
        // 배열을 값 목록으로 생성
        String[][] books = {
            {"책번호", "책제목", "저자", "출판사", "가격", "선호도"},
            {"책번호", "책제목", "저자", "출판사", "가격", "선호도"},
            {"책번호", "책제목", "저자", "출판사", "가격", "선호도"},
            {"책번호", "책제목", "저자", "출판사", "가격", "선호도"}
        };

        String[] bookInfo = {"책번호", "책제목", "저자", "출판사", "가격", "선호도"};

        책번호 : A000-소설, B100-인문, C200-과학, D300-컴퓨터
        */

        Scanner sc = new Scanner(System.in);

        int listLength = 0;//등록된 책 수량 카운트 변수
        int i = 0;//책 수량 카운트 변수(신규도서 등록할때 이용)

        //선호도 판별용 1차원 배열 생성
        int[] like = {};

        boolean logout = false; //로그아웃
        boolean hasBook = false;

        // 배열을 값 목록으로 생성
        String[][] books = {
                {"A000", "꼭두각시 살인사건", "다니엘 콜", "북플라자", "14000"},
                {"B100", "질서 너머", "조던 피터슨", "웅진지식하우스", "16000"},
                {"C200", "코스모스", "칼 세이건", "사이언스북스", "17000"},
                {"D300", "정보처리기사 실기", "정보처리기술사 연구회", "건기원", "30000"}
        };

        /*
        System.out.println("등록된 책 수: " + books.length);
        System.out.println("책번호: " + books[0][0]); // 원하는 책의 책번호: books[찾을 책이 있는 인덱스번호][0]
        */


        System.out.println("\n# 검색하실 책 번호를 입력하세요.");
        System.out.print("> ");
        String searchBookNum = sc.next(); // 검색할 책 번호 입력받기
        hasBook = false;//책 검색 결과 변수

        // 테스트 끝나면 books.length -> listLength 수정하기
        for (int idx = 0; idx < books.length; idx++) {
            if (books[idx][0].equals(searchBookNum)) { // 전체 책 중에서 입력받은 책 번호와 비교 후 찾으면
                hasBook = true;
                //like[idx]++;//선호도 측정을 위해 카운트해줌.

                // 찾은 책의 책 번호를 임시 저장 (String)
                String temp3 = books[idx][0];

                // 입력한 책 번호가 찾은 책 번호와 같으면 해당 배열 출력
                if (temp3.equals(searchBookNum)) {
                    System.out.println("\n============================================= 도 서 정 보 =============================================");
//                    System.out.println("  번  호 \t\t     제  목 \t\t     저  자 \t\t     출판사\t\t     가  격 \t\t   선호도");
                    System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%n", "번  호", "제  목", "저  자", "출판사", "가  격", "선호도");
                    System.out.println("=======================================================================================================");
                    for (String bookSearch : books[idx]) {
                        System.out.printf("%-15s", bookSearch);
                    }
                    System.out.println();
                }
            }
        }

        if (!hasBook) {
            System.out.println("\n해당 도서는 목록에 없습니다.\n");
        }

    }
}


