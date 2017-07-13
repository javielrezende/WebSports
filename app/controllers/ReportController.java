package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import it.innove.play.pdf.PdfGenerator;
import models.Cliente;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by Programador on 12/07/2017.
 */
@SubjectPresent
public class ReportController {

    @Inject
    public PdfGenerator pdfGenerator;

    public Result document() {
        List<Cliente> cliente = Cliente.find.fetch("usuario_id")
                .fetch("usuario_id.endereco_id")
                .orderBy("usuario_id.endereco_id.bairro").findList();
        return pdfGenerator.ok(views.html.report.report.render(cliente), "http://localhost:9000");

    }

}
