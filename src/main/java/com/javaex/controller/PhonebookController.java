package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 접수받는 일
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 파라미터 읽기 (action) 뭔지 알아야 됨
		String action = request.getParameter("action");
//		System.out.println(action);

		if ("list".equals(action)) {
			// 접수
			System.out.println("리스트 요청");

			// db데이터 가져오기
			PhonebookDao phonebookDao = new PhonebookDao();
			List<PersonVo> personList = phonebookDao.getPersonlist();

			// 화면그리기 --> 포워드
			// request에 list 주소("리스트 (임의)별명", personList) 넣기
			request.setAttribute("personList", personList);

			// 포워드
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/list.jsp");
			rd.forward(request, response);
		} else if ("writeform".equals(action)) {

			System.out.println("등록폼 요청, 저장해줘 아님");

			// 포워드
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/writeForm.jsp");
			rd.forward(request, response);

		} else if ("insert".equals(action)) {

			System.out.println("등록 요청 데이터 3개 저장해줘");

			// 나머지 파라미터 꺼내기

			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVo personVo = new PersonVo(name, hp, company);
			/*
			 * personVo.setName(name); personVo.setHp(hp); personVo.setCompany(company);
			 */

			// Dao를 메모리에 올린다.
			PhonebookDao phonebookDao = new PhonebookDao();
			// insertPerson() 사용해서 db에 저장한다.
			phonebookDao.insertPerson(personVo);

			// 리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");

			/*
			 * => 이 과정을 리다이렉트가 한다. getPersonList() 사용해서 전체 리스트를 가져온다.
			 * List<PersonVo>personList= phonebookDao.getPersonlist(); - 화면그리기 --> 포워드
			 * request에 list 주소("리스트 (임의)별명", personList) 넣기
			 * request.setAttribute("personList", personList); - 포워드 RequestDispatcher rd =
			 * request.getRequestDispatcher("WEB-INF/list.jsp"); rd.forward(request,
			 * response);
			 */

		} else if ("editform".equals(action)) {
			System.out.println("수정 !!폼!! 업무");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);

			// Dao를 메모리에 올린다.
			PhonebookDao phonebookDao = new PhonebookDao();

			// getPersonOne(no)로 1명 데이터의 주소를 가져온다.
			PersonVo personVo = phonebookDao.getPersonOne(no);

			// 화면 + 데이터 수정폼
			// request attribute 영역에 personVo 주소를 담는다.
			request.setAttribute("personVo", personVo);

			// 포워드 editForm.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editForm.jsp");
			rd.forward(request, response);

		} else if ("update".equals(action)) {
			System.out.println("수정");

			// 파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVo personVo = new PersonVo(no, name, hp, company);

			// phonebookDao를 메모리에 올린다.
			PhonebookDao phonebookDao = new PhonebookDao();

			// phonebookDao를 통해서 수정update을 시킨다.
			phonebookDao.updatePerson(personVo);

			// 리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");

		} else if ("delete".equals(action)) {
			System.out.println("삭제");

			// 파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));

			PersonVo personVo = new PersonVo(no);

			// phonebookDao를 메모리에 올린다.
			PhonebookDao phonebookDao = new PhonebookDao();

			// phonebookDao를 통해서 삭제delete을 시킨다.
			phonebookDao.deletePerson(personVo);

			// 리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");

		}else {
			System.out.println("action 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
