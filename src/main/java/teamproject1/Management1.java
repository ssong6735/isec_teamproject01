package main.java.teamproject1;

import java.util.Scanner;

public class Management1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        //회원관리 배열
        //계정코드가 administrator일 경우 관리자 계정
        String[][] memberData = {
                {"admin", "admin1234", "administrator"},
                {"user", "user123",}
        };
        //id, passwd, 계정 코드(user는 아직 미정)

        int memNum = 2;
        //권한 부여
        boolean adminAccess = false;

        boolean end = false;//프로그램 종료 판별 논리값
        boolean hasId = false;//로그인 시도 시 Id 존재여부
        boolean hasPasswd;
        String idInsert = null;
        //회원가입

        while (true) {
            boolean login = false; //로그인 여부 판단 논리값

            System.out.println("\n도서관리 프로그램을 이용해주셔서 감사합니다.");
            System.out.println("로그인 또는 회원가입을 진행해주세요.");
            while (true) {//로그인 or 회원가입 선택
                adminAccess = false;//재 로그인시 부여된 관리자 권한 상실
                System.out.println("# 1. [로그인]");
                System.out.println("# 2. [회원가입]");
                System.out.println("# 3. [프로그램 종료]");
//                System.out.println(memberData.length);

                System.out.print("> ");
                int loginChoice = sc.nextInt();

                switch (loginChoice) {
                    case 1://로그인
                        System.out.print("* 회원 ID >  ");
                        idInsert = sc.next();
                        hasId = false;

                        if (idInsert.equals(memberData[0][0])) {
                            hasId = true;

                            System.out.print("* 비밀번호 >  ");
                            String pwInsert = sc.next();

                            if (pwInsert.equals(memberData[0][1])) {
                                login = true;
                                adminAccess = true;
                                break;
                            } else {
                                System.out.println("- 잘못된 비밀번호입니다.\n");
                            }
                        }//로그인 시도 id가 관리자 id인지 여부 판별 if문 종료

                        //일반회원 로그인 과정
                        for (int i = 1; i < memberData.length; i++) {
                            if (memberData[i][0].equals(idInsert)) {
                                hasId = true;

                                System.out.print("* 비밀번호 > ");
                                String pwInsert = sc.next();

                                if (memberData[i][1].equals(pwInsert)) {
                                    login = true;
                                } else {
                                    System.out.println("- 잘못된 비밀번호입니다.\n");
                                }
                            }//일반회원 아이디 여부 판별 if문 종료
                        }
                        if (!hasId) {
                            System.out.println("- 해당 ID는 존재하지 않습니다.\n");
                        }

                        break;

                    case 2://회원가입
                        System.out.println("# 회원가입을 진행합니다.");

                        String[] member = new String[3];

                        boolean joinSuccess = false;//회원가입 성공 여부
                        while (true) {
                            System.out.print("* 회원 ID >  ");
                            String insertId = sc.next();

                            boolean idExist = false;
                            //중복체크
                            for (String[] temp : memberData) {
                                if (insertId.equals(temp[0])) {
                                    idExist = true;
                                }
                            }
                            if (idExist) {
                                System.out.println("이미 사용중인 ID입니다.\n");
                                continue;
                            } else {//중복된 id가 아닐경우 진행
                                member[0] = insertId;
//                                System.out.println(member[0]);//확인용 출력문 (임시)

                                while (true) {//비밀번호 입력 반복문
                                    System.out.print("* 비밀번호 > ");
                                    String insertPw = sc.next();
                                    System.out.print("* 비밀번호 확인 > ");
                                    String confirmPw = sc.next();
                                    if (confirmPw.equals(insertPw)) {
                                        member[1] = insertPw;

                                        //계정코드 생성
                                        int ran = (int) (Math.random() * 26) + 1;

                                        //코드 생성 종료
                                        //신규 회원 추가 및 이전
                                        String[][] temp2d = new String[memNum + 1][3];

                                        //기존 배열을 임시배열 temp2d로 복사
                                        int i = 0;
                                        for (String[] books1 : memberData) {
                                            int c = 0;
                                            for (String books2 : books1) {
                                                if (c < temp2d[i].length) {
                                                    temp2d[i][c] = books2;
                                                    c++;
                                                }
                                            }
                                            i++;
                                            if (i == temp2d.length) break;
                                        }

                                        int j = 0;
                                        for (String member1 : member) {
                                            temp2d[temp2d.length - 1][j] = member1;
                                            if (j == temp2d[temp2d.length - 1].length) break;
                                            j++;
                                        }

                                        member = null;
                                        memberData = temp2d;
                                        temp2d = null;
                                        memNum++;
                                        joinSuccess = true;
                                        break;

                                    } else {
                                        System.out.println("비밀번호가 다릅니다.\n");
                                        continue;
                                    }
                                }//비밀번호 반복 while문 종료
                            }

                            if (joinSuccess) break;
                        } //회원정보 입력 반복문 종료
                        System.out.println("- 회원가입이 완료되었습니다.\n");
                        break;
                    case 3://프로그램 종료
                        end = true;
                        break;
                    default:
                        System.out.println("- [로그인], [회원가입], [프로그램 종료] 중에서 선택해주세요.");
                        continue;
                }//로그인 switch문 종료
                if ((login) | (end)) break;//로그인 성공시 혹은 프로그램 종료시 반복문 종료
            }//로그인 while문 종료
            while (login) {
                if (adminAccess) {
                    System.out.println("\n환영합니다. 관리자 ID로 로그인하셨습니다.");
                } else {
                    System.out.println("\n환영합니다 " + idInsert + "님!");
                }
                String[][] books = new String[1000][6];//데이터베이스 2차배열
        /*books = new String[][]{
                {"A000", "꼭두각시 살인사건", "다니엘 콜", "북플라자", "14000"},
                {"B100", "질서 너머", "조던 피터슨", "웅진지식하우스", "16000"},
                {"C200", "코스모스", "칼 세이건", "사이언스북스", "17000"},
                {"D300", "정보처리기사 실기", "정보처리기술사 연구회", "건기원", "30000"}
        };*/
                int listLength = 0;//등록된 책 수량 카운트 변수
                int i = 0;//책 수량 카운트 변수(신규도서 등록할때 이용)

                //선호도 판별용 1차원 배열 생성
                int[] like = {};

                //회원관리 만들면 end를 logout으로 고쳐서 작동
                boolean logout = false; //로그아웃
                boolean hasBook = false;
//                System.out.println(books.length);//2차 배열의 길이
//        System.out.println(books[0].length);//책의 속성 수

                while (true) {
                    System.out.println("=====도서관리 프로그램=====");
                    System.out.println("# 1. 도서 신규 등록");
                    System.out.println("# 2. 모든 도서정보 보기");
                    System.out.println("# 3. 도서정보 검색");
                    System.out.println("# 4. 도서정보 수정");
                    System.out.println("# 5. 도서정보 삭제");
                    System.out.println("# 6. 로그아웃");
                    System.out.println("============================");
                    System.out.print("메뉴 입력: ");
                    int menuInsert = sc.nextInt();

                    switch (menuInsert) {
                        case 1://신규도서 등록
                            if (!adminAccess) {
                                System.out.println("\n도서 신규 등록은 관리자 권한이 필요합니다.");
                                continue;
                            }
                            System.out.println("# 신규도서 등록을 시작합니다.");
                            sc.nextLine();
                            //책 제목, 저자, 출판사 등에 Spacebar(공백문자)가 포함되어있는 경우가 있기 때문에 nextLine으로 값을 입력받아야 함.
                            //위의 메뉴선택 과정에서 입력한 Enter가 남아있기 때문에 nextLine을 한줄 추가해서 제거해줌.

                            String[] temp1 = new String[6];
                            System.out.print("- 책 번호: ");
                            temp1[0] = sc.nextLine();
                            System.out.print("- 책 제목: ");
                            temp1[1] = sc.nextLine();
                            System.out.print("- 저자: ");
                            temp1[2] = sc.nextLine();
                            System.out.print("- 출판사: ");
                            temp1[3] = sc.nextLine();
                            System.out.print("- 가격: ");
                            temp1[4] = sc.nextLine();
                            temp1[5] = " ";//일단 선호도 부분은 공백처리
                    /*선호도 적용을 위해 할 일
                    1. 검색횟수별로 선호도를 무엇이라고 표현할지 정하기 ex)인기, 화제 등
                    2. 선호도 판별용 1차원 배열을 생성하여 신규등록 도서에 매칭하기
                    3. 선호도 판별용 1차원 배열의 값 변화에 따라 선호도 상태 변경하기
                    4. 선호도 값의 변화코드를 전체 반복문의 마지막 부분에 적용(case3에서의 검색 횟수를 적용하기 위해)
                    */
                            // 선호도 판별 1차원 배열 정보 입력
                            int[] temp = new int[i + 1];
                            for (int j = 0; j < like.length; j++) {
                                temp[j] = like[j];
                            }
                            temp[temp.length - 1] = 0;
                            like = temp;
                            temp = null;

//                            System.out.println(Arrays.toString(like));

                            books[i] = temp1;
                            temp1 = null;
                            i++;
                            listLength++;
//                            System.out.println(listLength);//도서등록 후 배열길이 확인용 임시출력문
                            break;

                        case 2://전체 조회

                            for (int j = 0; j < listLength; j++) {
                                System.out.println("===============================도 서 정 보 ============================================================");
                                System.out.println("번호 \t\t 제목 \t\t     저자 \t\t       출판사\t\t   가격 \t\t     선호도");
                                System.out.println("=======================================================================================================");
                                for (int k = 0; k < books[j].length - 1; k++) {
                                    //선호도는 따로 출력하기 위해 반복문을 books[j].length - 1로 하였음.
                                    //함께 출력하려면 - 1 과 아래의 선호도 추가 출력문을 제외하면 됨.
                                    System.out.print(books[j][k] + "\t\t");
                                }
                                //선호도 출력문(미완성)
                                System.out.println(books[j][5]);
                                System.out.println();
                            }//반복출력 for문 종료
                            break;
                        case 3://도서정보 검색

                            System.out.println("# 검색하실 책 번호를 입력하세요.");
                            System.out.print("> ");
                            String searchBookNum = sc.next(); // 검색할 책 번호 입력받기
                            hasBook = false;//책 검색 결과 변수

                            for (int idx = 0; idx < listLength; idx++) {
                                if (books[idx][0].equals(searchBookNum)) { // 전체 책 중에서 입력받은 책 번호와 비교 후 찾으면
                                    hasBook = true;
                                    like[idx]++;//선호도 측정을 위해 카운트해줌.

                                    // 찾은 책의 책 번호를 임시 저장 (String)
                                    String temp3 = books[idx][0];

                                    // 입력한 책 번호가 찾은 책 번호와 같으면 해당 배열 출력
                                    if (temp3.equals(searchBookNum)) {
                                        System.out.println("=============================== 도 서 정 보 ===============================");
                                        System.out.println("책 번호 \t\t 책 제목 \t\t     저자 \t\t       출판사\t\t   가격");
                                        System.out.println("===========================================================================");
                                        for (String bookSearch : books[idx]) {
                                            System.out.print(bookSearch + "\t\t");
                                        }
                                        System.out.println("\n");

                                    }
                                }
                            }
                            if (!hasBook) {
                                System.out.println("\n해당 도서는 목록에 없습니다.\n");
                            }

                            break;
                        case 4://도서정보 수정
                            if (!adminAccess) {
                                System.out.println("\n도서정보 수정은 관리자 권한이 필요합니다.");
                                continue;
                            }
                            System.out.println("# 수정하실 책 번호를 입력하세요.");
                            System.out.print("> ");
                            String changeNum = sc.next();//수정할 책 번호
                            hasBook = false;//책 검색 결과 변수

                            int q = 0;
                            for (int j = 0; j < listLength; j++) {
                                if (books[j][0].equals(changeNum)) {// 배열의 0번인덱스가 입력받은 책 번호와 같다면
                                    hasBook = true;//검색결과를 true로 바꿔줌.

                                    //어떤 항목을 수정할지 결정
                                    System.out.println("\n- 해당 도서정보를 수정합니다.");
                                    while (true) { //수정 항목 잘못 입력시 반복, 취소 입력시 반복문 탈출하도록 코드 작성
                                        boolean changeEnd = false;
                                        System.out.println("- 어떤 정보를 수정하시겠습니까? 1. [제목], 2. [저자], 3. [출판사], 4. [가격], 5. 취소");
                                        System.out.print("> ");
                                        int changeChoice = sc.nextInt();
                                        sc.nextLine();

                                        switch (changeChoice) {
                                            case 1:
                                                System.out.println("# 제목을 수정합니다.");
                                                System.out.print("- 수정할 내용을 입력하세요 : ");
                                                String newTitle = sc.nextLine();
                                                books[j][1] = newTitle;
                                                break;
                                            case 2:
                                                System.out.println("# 저자를 수정합니다.");
                                                System.out.print("- 수정할 내용을 입력하세요 : ");
                                                String newWriter = sc.nextLine();
                                                books[j][2] = newWriter;
                                                break;
                                            case 3:
                                                System.out.println("# 출판사를 수정합니다.");
                                                System.out.print("- 수정할 내용을 입력하세요 : ");
                                                String newPublisher = sc.nextLine();
                                                books[j][3] = newPublisher;
                                                break;
                                            case 4:
                                                System.out.println("# 가격을 수정합니다.");
                                                System.out.print("- 수정할 내용을 입력하세요 : ");
                                                String newPrice = sc.next();
                                                books[j][4] = newPrice;
                                                break;
                                            case 5:
                                                changeEnd = true;
                                                break;
                                            default:
                                                System.out.println("1 ~ 5 사이의 숫자를 입력해주세요.\n");
                                        }//수정 내용 입력 스위치문 종료
                                        q = j;

                                        if (changeEnd) break;
                                    }//수정 내용 입력 while문 종료
                                    //return; 적용시 hasBook이 항상 true로 적용된다고 나옴
                                }//탐색 내용 유무 if문 종료
                            }//탐색 반복 for문 종료
                            if (!hasBook) { //hasBook이 여전히 false라면 해당 도서를 찾지 못한것.
                                System.out.println("해당 도서는 목록에 없습니다.\n");
                            }
                            System.out.println("=============================== 도 서 정 보 ===============================");
                            System.out.println("책 번호 \t\t 책 제목 \t\t     저자 \t\t       출판사\t\t   가격");
                            System.out.println("===========================================================================");
                            for (String bookSearch : books[q]) {
                                System.out.print(bookSearch + "\t\t");
                            }////////////////////////////////////////
                            System.out.println("\n");
                            System.out.println();
                            break;
                        case 5://도서정보 삭제
                            if (!adminAccess) {
                                System.out.println("\n도서정보 삭제는 관리자 권한이 필요합니다.");
                                continue;
                            }
                            System.out.println("# 삭제하실 책 번호를 입력하세요.");
                            System.out.print("> ");
                            String delNum = sc.next();//지울 책 번호
                            hasBook = false;//책 검색 결과 변수

                            for (int j = 0; j < listLength; j++) {
                                if (books[j][0].equals(delNum)) {
                                    hasBook = true;//해당 도서를 찾으면 논리값을 true로 변환
                                    for (int k = j; k < listLength - 1; k++) {
                                        books[k] = books[k + 1];
                                    }
                                    String[][] temp5 = new String[listLength - 1][];
                                    for (int k = 0; k < temp5.length; k++) {
                                        temp5[k] = books[k];
                                    }
                                    books = temp5;
                                    temp5 = null;
                                    //
                                    i--;//신규도서를 등록할 때 쓰이는 변수 i를 1줄여준다.
                                    listLength--;//도서를 삭제하였으므로 실질적으로 등록된 도서의 숫자를 -1해준다.
                                }
                            }
                            if (!hasBook) {
                                //해당 도서를 찾았다면 논리값이 true로 바뀌므로 여전히 false라면 찾지 못한 것이므로
                                //아래와 같이 출력함.
                                System.out.println("해당 도서는 목록에 없습니다.\n");
                            }
                            System.out.println();
                            break;
                        case 6:
                            logout = true;
                            System.out.println("현재 ID로부터 로그아웃합니다.\n\n");
                            break;
                        default:
                            System.out.println("메뉴를 잘못 입력했습니다.\n");
                            continue;
                    }//스위치문 종료

                    //선호도 갱신 코드
                    //while문의 마지막에 넣어서 작동(도서 검색에 넣으면 도서 신규등록시 반영이 안 됨)
                    for (int j = 0; j < listLength; j++) {
                        if (like[j] >= 50) {
                            books[j][5] = "스타 셀러";
                        } else if (like[j] >= 10) {
                            books[j][5] = "스테디 셀러";
                        } else if (like[j] >= 3) {
                            books[j][5] = "그로우 셀러";
                        } else if (like[j] < 3) {
                            books[j][5] = "비기닝 셀러";
                        }
                    }
                    //
                    if (logout) break;//전체 while문 종료

                }
                if (logout) break;//전체 while문 종료
            }
            if (end) {//프로그램 종료
                System.out.println("도서관리 프로그램을 종료합니다.");
                break;
            }
        }


    }
}
