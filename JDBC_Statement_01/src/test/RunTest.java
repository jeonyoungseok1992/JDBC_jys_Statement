package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RunTest {

	public static void main(String[] args) {
		/*///////////////
		 * JDBC용 객체
		 * -Connection : DB의 연결정보를 담고있는 객체                 //객체여서 앞에 대문자
		 * -[Prepared]Statement : 연결 된 DB에 SQL문을 전달해서 실행하고, 결과를 받아내는 객체
		 * -ResultSet : SELECT문 실행 후 조회 된 결과물들이 담겨있는 객체
		 * -result : int(처리 된 행 개수)
		 * 
		 * *JDBC 과정(!!순서중요!!)
		 * 1) JDBC driver 등록 : 해당 DBMS가 제공하는 클래스 등록    --우리는 오라클
		 * 2) Connection 생성 : 연결하고자 하는 DB정보를 입력해서 해당 DB와 연결과 동시에 생성  --우리는 오라클디벨럽먼트를 이용해서 DB에 접근
		 * 3) Statement 생성 : Connection 객체를 이용해서 생성(SQL문 실행 및 결과받는 객체)
		 * 4) SQL문 전달하면서 실행 : Statement 객체를 이용해서 SQL문 실행
		 * 5) 결과받기
		 * 		> SELECT문 실행 => ResultSet객체 (조회 된 데이터들이 담겨있음) => 6_1)
		 * 		> 	DML문      => int(처리 된 행의 갯수) => 6_2
		 * 
		 * 6_1) ResultSet에 담겨있는 데이터들을 하나씩 하나씩 뽑아서 vo객체에 차근차근 옮겨담기[+ ArrayList에 담아주기]
		 * 6_2) 트랜잭션 처리 (성공했다면 commit, 실패했다면 rollback 실행)
		 * 
		 * 7) 다 사용한 JDBC용 객체를 반드시 자원 반납(close) => 생성 된 역순으로
		 */
		/*
		// 1. 각자 pc(localhost)를 JDBC계정에 연결 한 후 TEST테이블에 insert 해보기
		// insert => 처리된 행 수(int) => 트랜잭션 처리
		
		//필요한 변수 먼저 세팅해준다
		int result = 0;  //결과(처리 된 행 수)를 받아 줄 변수
		Connection conn = null;  //DB의 연결정보를 보관 할 객체         			//import java.sql.Connection; 해준다
		Statement stmt = null;	//sql문 전달해서 실행 후 결과를 받는 객체								//ctr + shift + o 로 임포트
		
		// 앞으로 실행 할 sql문 작성해준다("완성형태"로 만둘어 두기)(맨 뒤에 세미콜론(SQL에서 쓰는)은 없어야된다!!
		//String sql = "INSERT INTO TEST VALUES(1, '김개똥', SYSDATE)";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("번호 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.println("이름 : ");
		String name = sc.next();
		
		String sql = "INSERT INTO TEST VALUES(" + num +", '" + name +"', SYSDATE)";
		System.out.println(sql);
		
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");  // ojdbc6.jar 파일을 추가 안했을 경우 | 추가는 했는데 오타가 났을 경우 => ClassNotFoundException발생
			//System.out.println("OracleDriver 등록성공");
			
			// 2) Connection객체 생성 : DB에 연결(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");           //import java.sql.DriverManager; 랑 익셉션&SQL익셉션 서라운드 해야함
			//커넥션 생겼다는건 자바와 오라클DB가 연결이 됐다는 뜻
			
			// 3) Statement객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) sql문 전달하면서 실행 후 결과받기(처리 된 행 갯수)
			result = stmt.executeUpdate(sql);
			//내가 실행 할 sql문이 dml문(insert, update, delete)일 경우 => executeUpdate("dml문") -> int
			//내가 실행 할 sql문이 select문 일 경우 => executeQuery("select문") -> ResultSet
			
			// 6) 트랜잭션 처리
			if(result > 0) {
				conn.commit();		// 요청sql문 성공 시 commit
			}
			else {
				conn.rollback();		// 요청 sql문 실패 시 rollback    --(실패한)쌓인 트랜잭션을 삭제
			}
			
			
		} catch (ClassNotFoundException e) {
			//System.out.println("OracleDriver 등록실패");
			e.printStackTrace();
		}
		//java.lang.ClassNotFoundException: oracle.jdbc.driver.OracleDriver 에러 남
		//Project의 Properties들어가서 java build path 통해서 ojdbc6(오라클 클래스)를 가져와야함
		  catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			
			
			try {
				// 7) 다 사용한 JDBC용 객체 자원반납(역순으로)
				stmt.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}
		
		
		if(result > 0) {
			System.out.println("데이터 삽입 성공");		
		}
		else {
			System.out.println("데이터 삽입 실패");	
		}
		*/
		
		
		// 2. 내 PC의 DB의 JDBC계정에 TEST테이블의 모든 데이터 조회해보기
		// select문 => 결과 ResultSet(조회 된 데이터들이 담겨있음) 받기 => ResultSet으로부터 데이터 뽑기
		
		//필요한 변수들 세팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;           //임포트 해줌
		
		//실행 할 SQL문 작성
		String sql = "SELECT * FROM TEST";
		//String sql = "SELECT * FROM TEST WHERE TNAME = '요네'";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			//System.out.println("OracleDriver 등록성공");
			
			// 2) Connection객체 생성 : DB에 연결(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");         

			
			// 3) Statement객체 생성
			stmt = conn.createStatement();
			
			//4, 5) SQL문 전달해서 실행 후 결과 받기 (ResultSet객체)
			rset = stmt.executeQuery(sql);          //지금 가리키는건 컬럼헤드
			
			//rset.next() => rset의 다음행이 들어있는지 없는지 확인 후 결과반환 + 한행을 내려준다
			//				 값이 있으면 true, 없으면 false
			while(rset.next()) {        				// 첫번째 1번 행 가리킴
			// 현재 참조하는 rset으로부터 어떤 컬럼에 해당하는 값을 어떤 타입으로 뽑을건지 제시해줘야한다.
			// DB의 컬럼명 제시(대소문자를 가리지 않는다)	--SQL자체가 컬럼명 대소문자를 안가림
				int tno = rset.getInt("tno");
				String tname = rset.getString("TNAME");
				Date tdate = rset.getDate("TDATE");          //Date타입이 없으므로 ctr shitf O 로 java.sql.임포트해온다
				
				System.out.println(tno + ", " + tname + ", " + tdate);
			
				
			}

			
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		  catch (SQLException e) {			
			e.printStackTrace();
		}
		 finally {
			 try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		 }
		
		
		
		
		
		
	}

}
