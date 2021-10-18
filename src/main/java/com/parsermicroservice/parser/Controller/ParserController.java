package com.parsermicroservice.parser.Controller;

import com.parsermicroservice.parser.Exceptions.ParserException;
import com.parsermicroservice.parser.Parser.Parser;
import com.parsermicroservice.parser.Repository.ParserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;




@RestController
@CrossOrigin
@RequestMapping("/parser")
public class ParserController {

    Logger myLogger = Logger.getLogger(String.valueOf(ParserController.class));

    @Autowired
    private ParserRepository myParserRepository;

    @Autowired
    private static CriteriaBuilder myCriteria;

    @Autowired
    private static CriteriaQuery myCriteriaQuery;

    @Autowired
    private static EntityManager myEntityManager;

    @Autowired
    private static Page myPage;

    @GetMapping("/list")
    public List<Parser> listParserData(){
        return myParserRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Parser getParseById(@PathVariable Long id) throws ParserException {
        return myParserRepository.findById(id).orElseThrow(() -> new ParserException("Item Not Found!"));
    }

    @PostMapping("/new")
    public Parser createParse(@RequestBody Parser myParserModel){
        return myParserRepository.save(myParserModel);
    }

    @PutMapping("/update/{id}")
    public Parser updateParse(@RequestBody Parser myParserModel, @PathVariable Long id){
        return myParserRepository.findById(id).map((parserModel) ->{
            parserModel.setLogDateTime(myParserModel.getLogDateTime());
            parserModel.setLogMessage(myParserModel.getLogMessage());
            return myParserRepository.save(parserModel);
        }).orElseGet(() ->{
            myParserModel.setParserID(id);
            return myParserRepository.save(myParserModel);

        });
    }





    @PostMapping("/file/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws ParserException {
        try {
            String fileDirectory = "";
            File myFile = new File(fileDirectory+file.getOriginalFilename());
            myFile.createNewFile();
            FileOutputStream myFileOutputStream = new FileOutputStream(myFile);
            myFileOutputStream.write(file.getBytes());
            myFileOutputStream.close();

            FileReader myReader = new FileReader(myFile);
            myReader.read();
            myReader.close();

            return new ResponseEntity<Object>("The File was uploaded Successfully!", HttpStatus.ACCEPTED);

        }catch(Exception ex){
            throw new ParserException("Error:" + ex);
        }

    }




    @GetMapping("/search")
    public List<Parser> searchParsedData(@RequestBody Parser parsedDataQuery){
        return myParserRepository.findAll(Example.of(parsedDataQuery));
    }


    

    @DeleteMapping("/delete/{id}")
    public void deleteParse(@PathVariable Long id){
        myParserRepository.deleteById(id);
    }


    @GetMapping("/count")
    public long getNoOfParsedData(){
        return myParserRepository.count();
    }
}
