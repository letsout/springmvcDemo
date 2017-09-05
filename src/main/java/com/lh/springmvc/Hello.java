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
	 * 	有@ModelAttribute标记的方法，会在每个目标方法执行之前被springmvc调用 
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false)Integer id, Map<String,Object> map) {
		if(id!=null) {
			System.out.println("ModelAttribute method");
			User user =new  User(1, "H", "123", "1@QQ.COM", "12");
			System.out.println("从数据库取得对象");
			map.put("user", user);
		}
	}
	/**
	 * SpringMVC 确定目标方法POJO类型入叄的过程
	 * 1.确定一个key
	 * 1).若目标方法的POJO类型的参数没有使用@ModelAttribute 作为修饰，则key为POJO类名的一个字母小写
	 * 2).若使用了@ModelAttribute 来修饰，则key为ModelAttribute 注解的value属性值
	 * 2.在implicModel中查找key对应的对象，若存在，则作为入叄传入
	 * 1).若在@modelAttribute 标记的方法中在Map中保存过，且key和确定的key一致，则会获取的到。
	 * 3.若implicModel中不存在key对应的对象，则检查当前的Handler是否使用@SessionAttributes注解修饰,
	 * 若使用了该注解，且@SessionAttribute注解的value属性值中包含了key，则会从HTTPSession中来获取key所对应
	 * value值，若存在则直接传入到目标方法的入叄中，若不存在则将抛出异常。
	 * 4.若Handler没有标识@SessionAttribute 注解或@SessionAttribute 注解的value值中不包含key，则会通过反射来创建POJO
	 * 类型的参数，传入目标方法的参数
	 * 5.SpringMVC会把key 和POJO类型的对象保存到implicModel中，进而会保存到request中
	 * 
	 * 
	 * 
	 * 
	 * springMvc源码分析
	 * 调用@ModelAttribute 注解修饰耳朵方法，四级上吧@ModelAttribute 方法中Map中的数据放在implicModel中。
	 * 2.解析请求处理器的目标参数，四级上该目标参数来自于webDateBinder 对象的target属性
	 * 1.).创建webDateBinder对象：
	 * ①。确定objectName属性：若传入的attrName 属性值为" ",则object为类名第一个字母小写。
	 * *注意：attrName.若目标方法的POJO属性使用了@ModelAttribute 来修饰，则attrName 值即为@ModelAttributede 的value属性值
	 * ②。确定target属性：
	 * >在implicModel中查找attrName 对应的属性值。若存在，ok
	 * >若不存在：则验证当前Handler是否使用了@SessionAttributes 进行修饰，若使用了，则尝试从session中
	 * 获取attrName所对应的属性值，若session中没有对应的属性值，者抛出异常、
	 * >若handler 没有使用@SessionAttribute 进行修饰，或@SessionAttribute 中没有使用value 值指定的key和attrName 相匹配。则通过反射创建poko对象
	 * 
	 * 2).SpringMVC 把表单的请求参数赋给了webDateBinder 的target对应属性
	 * 3).*SpringMVC会把webDateBinder的attrName和target给到implicModel，进而传到request域对象中
	 * 4).把webDateBinder的target作为参数传递给目标方法的入叄。
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/testModelAttribute",method=RequestMethod.POST)
	public String testModelAttribute(User user) {
		System.out.println("修改"+user);
		return SUCCESS;
	}
	/**
	 * @SessionAttribute 将值存放在session域中 v
	 * vaule:将指定的属性名放在session中
	 * type:将指定的属性对对象类型类型存放在session中
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
	 * 可以使用servlet原生api
	 * response
	 * request
	 * writer
	 * reader
	 * outputstream
	 * inputstream
	 * principal
	 * httpsession等
	 * 
	 */
	@RequestMapping("/testServletApi")
	public void testServletApi(HttpServletRequest request,
			HttpServletResponse response,Writer out) throws IOException {
		System.out.println("request:"+request+"response"+response);
		out.write("hello springmvc");
	}
	/**
	 * springmvc 会按请求参数名和pojo属性名自动匹配
	 * 自动为该对象填充属性值，支持级联属性
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
	 * name值为页面传递过来的属性值
	 * required:参数是否为必要参数，默认为true
	 * defaultValue:参数默认值
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
	