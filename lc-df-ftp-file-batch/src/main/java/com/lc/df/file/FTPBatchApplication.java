package com.lc.df.file;

import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
public class FTPBatchApplication {

	public static void main(String[] args) {
//		SpringApplication.run(FTPBatchApplication.class, args);
		RestTemplate template= new RestTemplate();
		String [] keys={"8a2a3620-76ac-4f50-877f-042719283655","19884666-3b30-4220-8436-f2a4eb24fd67","593e7b84-963d-48e7-894a-8b26fbd0c63c","85a97639-574a-4e89-afa3-e6538d32c390","9116735c-0d0a-4a3d-965a-7ea85c8742b4","e1ae1746-74ad-4b47-8cbc-20a1f7dcbb40","9c02af5c-2bc3-496d-bfd6-5177b7b68a84","d24df23e-0002-46de-993e-a172a5f6082c","0821ff71-18f3-4c90-84e1-e520ba5f6137","724ef513-52ed-4731-b2d6-83b9267dd66e","4deeb88b-d0db-44e3-8aa4-8456cdea9ee6","704f0fad-5215-4695-a603-9f0005a3f25e","6bf72b56-31b9-4c3f-8728-11c40712172e","2280c1f0-2606-46df-9d56-7d1f784403c7","740c3bd2-b81b-409a-8adc-6033cf81e385","d2f90692-46aa-45c4-8ea0-78a6fb7515c0","7ad63167-c43d-4cde-8f03-9170e6f636fd","a74dc26c-f3e4-4063-be78-4a7943d19bfa","0d893b82-ab78-48a3-b7d0-6a78886cf301","b9c39ba3-e00f-40fe-9258-859cda704d0f","a5795561-327c-43f0-aade-c0143682e6e3","22310fbc-0c52-4d4d-966d-ef14ddcca7df","be311791-b67e-4821-ad08-2ae3b7d782c2","72768392-c24e-44a4-9ebe-b656ad1a72f4","fa30a27a-0fee-4a5b-b051-444cdd2580d1","9cb49889-8c63-4b7d-88c3-15fc144a3158","a777bb69-252e-42d7-ae51-c3990893161b","04239cb1-b180-4323-bd29-21847a8933a0","0398fcb1-e9c2-463b-afb9-9cc9191fd6dc","cf14a483-8cbe-43ac-8c82-11591b104ac5","9a3beacf-7a06-48c3-a0aa-ba270438be79","e286ef67-f31a-4eeb-8dc4-54a0d08bda8b","781dc5e4-dba2-4af4-bafe-af5b2bf1fba3","28a050cb-ecc6-4826-8489-0009b49b7301","b49905b4-cc61-4430-b7fe-8bb07111006d","64d97575-6055-4ad0-96ed-94ea1582fe09","8c202a59-0a0d-401d-9222-3717a4393521","391b6129-373f-4f49-bb8b-688d21c04090","7b5630a1-b870-4fe3-a802-78cc0a2882de","bb285acd-6809-48cb-80e4-c2056751caed","2f048025-979d-402d-934c-942ec3064893","972e0a35-2918-4eba-a6a2-0dfce6f511e9","924fa9c8-a74b-4ae7-bbec-9685429a1ffc","7b2ef064-ea7b-4491-9875-4957ad8b1c18","6b8131d4-32c2-4709-9a3a-ffe491cac265"};
		for(String key:keys){
			System.out.println(template.getForObject("http://localhost:8080/customers/delete/" + key, String.class));
		}
		
	}
}
