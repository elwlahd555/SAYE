package com.gokchu.saye.test;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.web.bind.annotation.GetMapping;

public class TestJython {

	private static PythonInterpreter intPre;

	@GetMapping("/testJython")
	public String getTest() {
		System.out.println("들어옴");

		intPre = new PythonInterpreter();
		intPre.execfile("src/main/com/gokchu/saye/test/test.py");
		intPre.exec("print(testFunc(5,10))");

		PyFunction pyFuntion = (PyFunction) intPre.get("testFunc", PyFunction.class);
		int a = 10, b = 20;
		PyObject pyobj = pyFuntion.__call__(new PyInteger(a), new PyInteger(b));
		System.out.println(pyobj.toString());

		return pyobj.toString();
	}
}
