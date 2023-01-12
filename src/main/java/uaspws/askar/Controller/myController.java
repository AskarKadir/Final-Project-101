/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uaspws.askar.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uaspws.askar.JpaController.PendudukJpaController;
import uaspws.askar.Model.Penduduk;

/**
 *
 * @author askar
 */


@RestController
@RequestMapping("/penduduk")
public class myController {
    
    Penduduk penduduk = new Penduduk();
    
    PendudukJpaController  jpacontroller = new PendudukJpaController();
    
    @GetMapping
    public List<Penduduk> getData(){
        List<Penduduk> data = new ArrayList<>();
        try{
            data = jpacontroller.findPendudukEntities();
        }catch (Exception e){
            
        }
        return data;
    }
    
    
    //get mapping id
    @GetMapping("/{id}")
    public List<Penduduk> getPendudukEntities(@PathVariable("id")int id){
        List<Penduduk> dataa = new ArrayList<Penduduk>(); //new object
        try{
            penduduk = jpacontroller.findPenduduk(id);
        dataa.add(penduduk);
        }catch (Exception e){}
        return dataa;
    }
    
    
    //post mapping
    @PostMapping
    public String insertData(HttpEntity<String> requestdata){
        String pesan = "Data berhasil ditambahkan";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            penduduk = map.readValue(json_receive, Penduduk.class);
            jpacontroller.create(penduduk);
        }catch(Exception e){
            pesan = "Data gagal ditambahkan. Eror + "+e;
        }
        return pesan;
    }
    
    @PutMapping
    public String updateData(HttpEntity<String> requestdata){
        String pesan = "Data telah diperbaharui";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            penduduk = map.readValue(json_receive, Penduduk.class);
            jpacontroller.edit(penduduk);
        }catch(Exception e){
            pesan = "Data gagal diperbaharui. Eror : + "+e;
        }
        return pesan;
    }
    
    @DeleteMapping
    public String deleteData(HttpEntity<String> requestdata){
        String pesan = "Data telah dihapus";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            penduduk = map.readValue(json_receive, Penduduk.class);
            jpacontroller.destroy(penduduk.getId());
        }catch(Exception e){
            pesan = "Data gagal dihapus. Eror : "+e;
        }
        return pesan;
    }
    
}
