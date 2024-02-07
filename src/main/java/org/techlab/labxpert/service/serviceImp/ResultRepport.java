package org.techlab.labxpert.service.serviceImp;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.techlab.labxpert.Enum.StatutEchantillon;
import org.techlab.labxpert.Enum.StatutNumeration;
import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.RapportDTO;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.service.I_Analyse;
import org.techlab.labxpert.service.I_Rapport;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResultRepport implements I_Rapport {
    @Autowired
    I_Analyse i_analyse;
    public byte[] exportReport(Long id) {
        try {
            String path = "D:\\Simplon Tasks";
            List<Object[]> results = i_analyse.printResultRepport(id);
            List<RapportDTO> rapportDTOS = new ArrayList<>();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Object[] result : results) {
                RapportDTO rapportDTO = new RapportDTO();
                rapportDTO.setNom_analyse(result[0].toString());
                rapportDTO.setNom(result[1].toString());
                rapportDTO.setPrenom(result[2].toString());
                rapportDTO.setTel(result[3].toString());
                rapportDTO.setAdresse(result[4].toString());
                Date date = inputFormat.parse(result[5].toString());
                rapportDTO.setDate_naissance(date);
                rapportDTO.setStatut(StatutNumeration.valueOf(result[6].toString()).toString());
                rapportDTO.setValue(Double.parseDouble(result[7].toString()));
                rapportDTO.setLibelle(result[8].toString());
                rapportDTO.setMax_value(Double.parseDouble(result[9].toString()));
                rapportDTO.setMin_value(Double.parseDouble(result[10].toString()));
                rapportDTO.setUnite(result[11].toString());
                rapportDTOS.add(rapportDTO);
            /*for (Object field : result) {
                System.out.print(field + " ");
            }
            System.out.println();*/
            }
            //System.out.println(rapportDTOS);
            //load file and compile it
            File file = ResourceUtils.getFile("classpath:ResultatRapport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rapportDTOS);
            Map<String, Object> parameters = new HashMap<>();
            //parameters.put("QUERY", "select nom_analyse,nom,prenom,tel,adresse,date_naissance,statut,value,libelle,max_value,min_value,unite from analyses,numerations,norme,echantillons,patients where analyses.id_analyse=numerations.analyse_id_analyse and numerations.norme_id_norme=norme.id_norme and analyses.id_analyse=7 and analyses.echantillon_id_echantillon=echantillons.id_echantillon and echantillons.patient_id=patients.id");
            parameters.put("createdBy", "Mohammed Talaini");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            //if (reportFormat.equalsIgnoreCase("html")) {
            System.out.println(jasperPrint.toString());
            //JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Sales.pdf");
            byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
        /*}
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Sales.pdf");
        }*/

            return reportContent;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return new byte[0];
    }
}
