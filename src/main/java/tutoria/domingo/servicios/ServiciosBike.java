/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutoria.domingo.modelo.Bike;
import tutoria.domingo.repositorio.RepositorioBike;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosBike {
     @Autowired
    private RepositorioBike metodosCrud;
    
    public List<Bike> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Bike> getBikes(int bike){
        return metodosCrud.getBikes(bike);
    }
    
    public Bike save(Bike bike){
        if(bike.getId()==null){
            return metodosCrud.save(bike);
        }else{
            Optional<Bike> evt=metodosCrud.getBikes(bike.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(bike);
            }else{
                return bike;
            }
        }
    }
    public Bike update(Bike bike){
        if(bike.getId()!=null){
            Optional<Bike> e=metodosCrud.getBikes(bike.getId());
            if(!e.isEmpty()){
                if(bike.getName()!=null){
                    e.get().setName(bike.getName());
                }
                if(bike.getBrand()!=null){
                    e.get().setBrand(bike.getBrand());
                }
                if(bike.getYear()!=null){
                    e.get().setYear(bike.getYear());
                }
                if(bike.getDescription()!=null){
                    e.get().setDescription(bike.getDescription());
                }
                if(bike.getCategory()!=null){
                    e.get().setCategory(bike.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return bike;
            }
        }else{
            return bike;
        }
    }


    public boolean deleteBike(int bikeId) {
        Boolean aBoolean = getBikes(bikeId).map(bike -> {
            metodosCrud.delete(bike);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
