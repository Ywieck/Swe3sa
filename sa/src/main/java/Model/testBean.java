package Model;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class testBean {

	private List<test> tests;
	
	testBean() {
		tests = new ArrayList<test>();
		tests.add(new test("Enno"));
		tests.add(new test("Yasar"));
	}
	
	public List<test> getTests() {
		return tests;
	}

}
