package main.java.test;

import java.util.Scanner;

public class MemberProcess {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        //회원관리 배열
        //계정코드가 administrator일 경우 관리자 계정
        String[][] memberData = {{"admin", "admin1234", "administrator"}};//id, passwd, 계정 코드

        int memNum = 1;
        //권한 부여
        boolean adminAccess = false;

        boolean end = false;//프로그램 종료 판별 논리값
        boolean hasId = false;//로그인 시도 시 Id 존재여부
        boolean hasPasswd;
        boolean login = false; //로그인 여부 판단 논리값
        //회원가입

        while (true) {

            System.out.println("환영");
            while (true) {//로그인 or 회원가입 선택
                adminAccess = false;//재 로그인시 부여된 관리자 권한 상실
                System.out.println("# 1. [로그인]");
                System.out.println("# 2. [회원가입]");
                System.out.println("# 3. [프로그램 종료]");
                System.out.println(memberData.length);

                System.out.print("> ");
                int loginChoice = sc.nextInt();

                switch (loginChoice) {
                    case 1://로그인
                        System.out.print("* 회원 ID >  ");
                        String idInsert = sc.next();
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
                                System.out.println("- 잘못된 비밀번호입니다.");
                            }
                        }//로그인 시도 id가 관리자 id인지 여부 판별 if문 종료

                        for (int i = 0; i < memberData.length; i++) {
                            if (memberData[i][0].equals(idInsert)) {
                                hasId = true;

                                System.out.print("* 비밀번호 > ");
                                String pwInsert = sc.next();

                                if (memberData[i][1].equals(pwInsert)) {
                                    login = true;
                                } else {
                                    System.out.println("- 잘못된 비밀번호입니다.");
                                }
                            }//일반회원 아이디 여부 판별 if문 종료
                        }
                        if (!hasId) {
                            System.out.println("- 해당 ID는 존재하지 않습니다.");
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
                                System.out.println("이미 사용중인 ID입니다.");
                                continue;
                            } else {//중복된 id가 아닐경우 진행
                                member[0] = insertId;
                                System.out.println(member[0]);//확인용 출력문 (임시)

                                while (true) {//비밀번호 입력 반복문
                                    System.out.print("* 비밀번호 > ");
                                    String insertPw = sc.next();
                                    System.out.print("* 비밀번호 확인 > ");
                                    String confirmPw = sc.next();
                                    if (confirmPw.equals(insertPw)) {
                                        member[1] = insertPw;

                                        //계정코드 생성
                                        int ran = (int)(Math.random() * 26) + 1;

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
                                        memberData = temp2d; temp2d = null;
                                        memNum++;
                                        joinSuccess = true;
                                        break;

                                    } else {
                                        System.out.println("비밀번호가 다릅니다.");
                                        continue;
                                    }
                                }//비밀번호 반복 while문 종료
                            }

                            if (joinSuccess) break;
                        } //회원정보 입력 반복문 종료
                        System.out.println("- 회원가입이 완료되었습니다.");
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
                    System.out.println("환영합니다 관리자 ID로 로그인하셨습니다.");
                }
                break;
            }
            if (end) {//로그아웃
                System.out.println("도서관리 프로그램을 종료합니다.");
                break;
            }
        }


    }
}
