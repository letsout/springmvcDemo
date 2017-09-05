package com.lh.springmvc;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.lh.entities.User;
@SessionAttributes(value={"user"},types=String.class)
@RequestMapping("/hello")
@Controller
public class Hello {
	
	private static final String SUCCESS="success";
	@RequestMapping("/testRedirect")
	public String testRedirect() {
		System.out.println("testRedirect");
		return "redirect:/index.jsp";
	}
	@RequestMapping("/testHelloView")
	public String testHelloView() {
		System.out.println("testHelloView");
		return "helloView";
	}
	@RequestMapping("/testViewAndViewResolver")
	public String testViewAndViewResolver() {
		System.out.println("testViewAndViewResolver");
		return SUCCESS;
	}
	/**
	 * 	��@ModelAttribute��ǵķ���������ÿ��Ŀ�귽��ִ��֮ǰ��springmvc���� 
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false)Integer id, Map<String,Object> map) {
		if(id!=null) {
			System.out.println("ModelAttribute method");
			User user =new  User(1, "H", "123", "1@QQ.COM", "12");
			System.out.println("�����ݿ�ȡ�ö���");
			map.put("user", user);
		}
	}
	/**
	 * SpringMVC ȷ��Ŀ�귽��POJO�����녣�Ĺ���
	 * 1.ȷ��һ��key
	 * 1).��Ŀ�귽����POJO���͵Ĳ���û��ʹ��@ModelAttribute ��Ϊ���Σ���keyΪPOJO������һ����ĸСд
	 * 2).��ʹ����@ModelAttribute �����Σ���keyΪModelAttribute ע���value����ֵ
	 * 2.��implicModel�в���key��Ӧ�Ķ��������ڣ�����Ϊ�녣����
	 * 1).����@modelAttribute ��ǵķ�������Map�б��������key��ȷ����keyһ�£�����ȡ�ĵ���
	 * 3.��implicModel�в�����key��Ӧ�Ķ������鵱ǰ��Handler�Ƿ�ʹ��@SessionAttributesע������,
	 * ��ʹ���˸�ע�⣬��@SessionAttributeע���value����ֵ�а�����key������HTTPSession������ȡkey����Ӧ
	 * valueֵ����������ֱ�Ӵ��뵽Ŀ�귽�����녣�У������������׳��쳣��
	 * 4.��Handlerû�б�ʶ@SessionAttribute ע���@SessionAttribute ע���valueֵ�в�����key�����ͨ������������POJO
	 * ���͵Ĳ���������Ŀ�귽���Ĳ���
	 * 5.SpringMVC���key ��POJO���͵Ķ��󱣴浽implicModel�У������ᱣ�浽request��
	 * 
	 * 
	 * 
	 * 
	 * springMvcԴ�����
	 * ����@ModelAttribute ע�����ζ��䷽�����ļ��ϰ�@ModelAttribute ������Map�е����ݷ���implicModel�С�
	 * 2.��������������Ŀ��������ļ��ϸ�Ŀ�����������webDateBinder �����target����
	 * 1.).����webDateBinder����
	 * �١�ȷ��objectName���ԣ��������attrName ����ֵΪ" ",��objectΪ������һ����ĸСд��
	 * *ע�⣺attrName.��Ŀ�귽����POJO����ʹ����@ModelAttribute �����Σ���attrName ֵ��Ϊ@ModelAttributede ��value����ֵ
	 * �ڡ�ȷ��target���ԣ�
	 * >��implicModel�в���attrName ��Ӧ������ֵ�������ڣ�ok
	 * >�������ڣ�����֤��ǰHandler�Ƿ�ʹ����@SessionAttributes �������Σ���ʹ���ˣ����Դ�session��
	 * ��ȡattrName����Ӧ������ֵ����session��û�ж�Ӧ������ֵ�����׳��쳣��
	 * >��handler û��ʹ��@SessionAttribute �������Σ���@SessionAttribute ��û��ʹ��value ֵָ����key��attrName ��ƥ�䡣��ͨ�����䴴��poko����
	 * 
	 * 2).SpringMVC �ѱ����������������webDateBinder ��target��Ӧ����
	 * 3).*SpringMVC���webDateBinder��attrName��target����implicModel����������request�������
	 * 4).��webDateBinder��target��Ϊ�������ݸ�Ŀ�귽�����녣��
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/testModelAttribute",method=RequestMethod.POST)
	public String testModelAttribute(User user) {
		System.out.println("�޸�"+user);
		return SUCCESS;
	}
	/**
	 * @SessionAttribute ��ֵ�����session���� v
	 * vaule:��ָ��������������session��
	 * type:��ָ�������ԶԶ����������ʹ����session��
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String,Object> map) {
		User user = new User("Tom","123","1@qq.com","12");
		map.put("user", user);
		map.put("school", "school");
		return SUCCESS;
	}
	
	@RequestMapping("/testMap")
	public String testMap(Map<String ,Object> map) {
		map.put("names",Arrays.asList("tom","jerry","lucy"));
		return SUCCESS;
	}
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("time",new Date());
		return mv;
	}
	/**
	 * ����ʹ��servletԭ��api
	 * response
	 * request
	 * writer
	 * reader
	 * outputstream
	 * inputstream
	 * principal
	 * httpsession��
	 * 
	 */
	@RequestMapping("/testServletApi")
	public void testServletApi(HttpServletRequest request,
			HttpServletResponse response,Writer out) throws IOException {
		System.out.println("request:"+request+"response"+response);
		out.write("hello springmvc");
	}
	/**
	 * springmvc �ᰴ�����������pojo�������Զ�ƥ��
	 * �Զ�Ϊ�ö����������ֵ��֧�ּ�������
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/testPojo",method=RequestMethod.POST)
	public String testPojo(User user) {
		System.out.println(user);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String cl) {
		System.out.println("Content-Language:"+cl);
		return SUCCESS;
	}
	
	/**
	 * nameֵΪҳ�洫�ݹ���������ֵ
	 * required:�����Ƿ�Ϊ��Ҫ������Ĭ��Ϊtrue
	 * defaultValue:����Ĭ��ֵ
	 */
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(@RequestParam(name="username") String username,@RequestParam(name="age",defaultValue="0",required=false) int age) {
		System.out.println("username:"+username+"   age:"+age+"");
		return SUCCESS;
	}
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable int id) {
		System.out.println("testRestDelete "+id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable int id) {
		System.out.println("testRestPut "+id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testRestPost() {
		System.out.println("testRestPost");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
	public String testRestGet(@PathVariable int id) {
		System.out.println("testRestGet "+id);
		return SUCCESS;
	}
	
	
	
	@RequestMapping("/pathVariable/{id}")
	public String testPathVariable(@PathVariable("id") int id) {
		System.out.println("tetsPathVariable"+id);
		return SUCCESS;
	}
	
	@RequestMapping("/test")
	public String Hello() {
		System.out.println("hello ");
		return SUCCESS;
	}
}
	