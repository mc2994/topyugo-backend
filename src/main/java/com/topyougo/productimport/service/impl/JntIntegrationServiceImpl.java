package com.topyougo.productimport.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.topyougo.productimport.dto.DataResponse;
import com.topyougo.productimport.dto.ResponseDetails;
import com.topyougo.productimport.service.JntIntegrationService;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@Service
public class JntIntegrationServiceImpl implements JntIntegrationService {

    @Override
    public DataResponse fetchTrackingInfo(String orderNo) {

	DataResponse jntResponse = new DataResponse();
	try {
//			Unirest.config().reset();
//			Unirest.config()
//	           .socketTimeout(120000)
//	           .connectTimeout(120000);
	    HttpResponse<String> response = Unirest.post("https://www.jtexpress.ph/index/router/index.html")
		    .header("Cookie", "__cfduid=dc8636362bb4d0f6d7b1a263be70082261595253210").multiPartContent()
		    .field("data[lang]", "en").field("data[source]", "3").field("data[billcode]", orderNo)
		    .field("method", "app.findTrack").asString();

	    JSONObject jsonObject = new JSONObject(response.getBody());
	    String data = jsonObject.getString("datas");

	    Gson gson = new Gson();
	    DataResponse parsedData = gson.fromJson(data, DataResponse.class);

	    if (!parsedData.getDetails().isEmpty()) {
		Optional<ResponseDetails> firstRecord = parsedData.getDetails().stream().findFirst();
//				System.out.println(UtilityClass.toJson(parsedData.getBillcode()));
//				System.out.println(UtilityClass.toJson(firstRecord.get()));

		List<ResponseDetails> details = new ArrayList<ResponseDetails>();
		details.add(firstRecord.get());

		jntResponse.setBillcode(parsedData.getBillcode());
		jntResponse.setDetails(details);

	    } else {
		jntResponse.setBillcode(parsedData.getBillcode());
		jntResponse.setDetails(new ArrayList<ResponseDetails>());
	    }

	    // Optional<ResponseDetails> firstRecord =
	    // jntResponse.getDetails().stream().findFirst();
//
//			if(firstRecord.isPresent()) {
//				System.out.println(firstRecord.get().getScanstatus());
//				System.out.println(UtilityClass.toJson(jntResponse));	
//			}

	} catch (Exception err) {
	    err.printStackTrace();
	}

	System.out.println("PARSED SiZE: " + jntResponse.getDetails().size());
	return jntResponse;
    }
}
