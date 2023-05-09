package fit.cvut.EventPro.service.email;


import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class EmailTemplate {

	private String templateId;

	private String template;

	private Map<String, String> replacementParams;

	private String NOEMAILTEMPLATE = "No Email Template";

	public EmailTemplate(String templateId) {
		this.templateId = templateId;
		try {
			this.template = loadTemplate(templateId);
		} catch (Exception e) {
			e.printStackTrace();
			this.template = NOEMAILTEMPLATE;
		}
	}

	private String loadTemplate(String templateId) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File("/var/lib/jenkins/workspace/IGIVE-STAGING/src/main/resources/templates/" + templateId);
		//	File file = new File("Users/nomanshah/IdeaProjects/igiveu-server/src/main/resources/templates/" + templateId);
		String content = NOEMAILTEMPLATE;
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Could not read template with ID = " + templateId);
		}
		return content;
	}

	public String getTemplate(Map<String, String> replacements) {
		String cTemplate = this.getTemplate();

		if (!StringUtils.isEmpty(cTemplate)) {
			for (Map.Entry<String, String> entry : replacements.entrySet()) {
				try {
					cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
				} catch (Exception ex) {

				}
			}
		}


		return cTemplate;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, String> getReplacementParams() {
		return replacementParams;
	}

	public void setReplacementParams(Map<String, String> replacementParams) {
		this.replacementParams = replacementParams;
	}

}
