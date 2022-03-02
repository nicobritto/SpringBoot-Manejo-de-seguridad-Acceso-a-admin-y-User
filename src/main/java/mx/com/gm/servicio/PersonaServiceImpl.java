package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl  implements PersonaService{
    
    @Autowired
    private PersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)//lleve readOnly porque no modifica los datos
    public List<Persona> listarPersonas() {
        return(List<Persona>) personaDao.findAll();
        
    }

    @Override
    @Transactional//guardar necesita transactional para que se inicie la transac y guarde datos
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
     @Transactional//guardar necesita transactional para que se inicie la transac y elimine datos
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)//lleve readOnly porque no modifica los datos
    public  Persona  encontrarPersona(Persona persona) {
        // si no encuentra el objeto contrado con orElse(null); va regresar nul
       return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
    
    
}
