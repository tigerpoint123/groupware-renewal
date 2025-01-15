package com.ll.groupware_renewal.util;

import com.ll.groupware_renewal.dto.Board;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.*;

@Component("fileUtils")
public class BFileUtils {
	private static final String filePath = "C:\\mju\\"; // 파일이 저장될 위치

	public List<Map<String, Object>> InsertFileInfo(Board board, MultipartHttpServletRequest mpRequest)
			throws Exception {

		Iterator<String> Iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String OriginalFileName = null;
		String OriginalFileExtension = null;
		String StoredFileName = null;

		List<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> ListMap = null;

		int Bno = board.getBno();

		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (Iterator.hasNext()) {
			multipartFile = mpRequest.getFile(Iterator.next());
			if (multipartFile.isEmpty() == false) {
				OriginalFileName = multipartFile.getOriginalFilename();
				OriginalFileExtension = OriginalFileName.substring(OriginalFileName.lastIndexOf("."));
				StoredFileName = getRandomString() + OriginalFileExtension;
				file = new File(filePath + StoredFileName);
				multipartFile.transferTo(file);
				ListMap = new HashMap<String, Object>();
				ListMap.put("Bno", Bno);
				ListMap.put("OriginalFileName", OriginalFileName);
				ListMap.put("StoredFileName", StoredFileName);
				ListMap.put("FileSize", multipartFile.getSize());
				List.add(ListMap);
			}
		}
		return List;
	}

	public List<Map<String, Object>> UpdateFileInfo(Board board, String[] fileList, String[] fileNameList,
			MultipartHttpServletRequest mpRequest) throws Exception {
		Iterator<String> Iterator = mpRequest.getFileNames();
		MultipartFile multipartFile = null;
		String OriginalFileName = null;
		String OriginalFileExtension = null;
		String StoredFileName = null;
		List<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> ListMap = null;
		int Bno = board.getBno();
		while (Iterator.hasNext()) {
			multipartFile = mpRequest.getFile(Iterator.next());
			if (multipartFile.isEmpty() == false) {
				OriginalFileName = multipartFile.getOriginalFilename();
				OriginalFileExtension = OriginalFileName.substring(OriginalFileName.lastIndexOf("."));
				StoredFileName = getRandomString() + OriginalFileExtension;
				multipartFile.transferTo(new File(filePath + StoredFileName));
				ListMap = new HashMap<String, Object>();
				ListMap.put("IsNew", "1");
				ListMap.put("Bno", Bno);
				ListMap.put("OriginalFileName", OriginalFileName);
				ListMap.put("StoredFileName", StoredFileName);
				ListMap.put("FileSize", multipartFile.getSize());
				List.add(ListMap);
			}
		}
		if (fileList != null && fileNameList != null) {
			for (int i = 0; i < fileNameList.length; i++) {
				ListMap = new HashMap<String, Object>();
				ListMap.put("IsNew", "0");
				ListMap.put("BFileID", fileList[i]);
				List.add(ListMap);
			}
		}
		return List;
	}

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}