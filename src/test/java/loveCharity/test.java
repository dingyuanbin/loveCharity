package loveCharity;

import java.io.IOException;
import java.util.List;

import controller.PatientController;
import entity.ImageInfo;
import org.junit.Test;

import service.PatientService;
import service.impl.ImageInfoServiceImpl;
import service.impl.PatientServiceImpl;
import utils.ImageProcessing;

public class test {
@Test
public void test() throws Exception {
	PatientService patientService=new PatientServiceImpl();
	patientService.findAll();
}
}
