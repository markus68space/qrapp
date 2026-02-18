package org.me.qrapp.view;

import java.nio.file.Path;

import org.me.qrapp.model.Vcard;
import org.me.qrapp.service.QrCodeServiceImpl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.InputField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("qrApp")
public class HomeView extends VerticalLayout {

	// Vcard
	private TextField formattedNameTF = new TextField("FormattedName");
	private TextField telTF = new TextField("TEL");
	private TextField emailTF = new TextField("EMAIL");
	
	private Vcard vcard;
	private Span vcardSpan = new Span("");
	
	// Qrcode
	private TextField outputImageFileTF = new TextField("OutputImageFile");
	
	private QrCodeServiceImpl qrCodeServiceImpl;
	private Span qrcodeSpan = new Span("");
	
	public HomeView() {
		
		Span span = new Span("qrApp");
		
		HorizontalLayout actionsVcardHL = new HorizontalLayout();
		Button applyVcard = new Button("Apply");
		Button cancelVcard = new Button("Cancel");
		actionsVcardHL.add(applyVcard,cancelVcard);
		
		HorizontalLayout actionsQrcodeHL = new HorizontalLayout();
		Button generateQrcode = new Button("Generate");
		Button cancelQrcode = new Button("Cancel");
		actionsQrcodeHL.add(generateQrcode, cancelQrcode);
		
		// vcard
		add(span,
			formattedNameTF,
			telTF,
			emailTF,
			actionsVcardHL,
			vcardSpan
			);
		
		// qrcode
		add(outputImageFileTF,
			actionsQrcodeHL,
			qrcodeSpan);
		
		// vcard
		cancelVcard.addClickListener(e -> {
			formattedNameTF.setValue("");
			telTF.setValue("");
			emailTF.setValue("");
			vcardSpan.setText("");
			
		});
		
		applyVcard.addClickListener(e -> {
			vcard = new Vcard();
			vcard.setFN(formattedNameTF.getValue());
			vcard.setTEL(telTF.getValue());
			vcard.setEMAIL(emailTF.getValue());
			vcardSpan.setText(vcard.toString());
			
		});
		
		// qrcode
		cancelQrcode.addClickListener(e -> {
			outputImageFileTF.setValue("");
			qrcodeSpan.setText("");
		});
		
		generateQrcode.addClickListener(e-> {
			qrCodeServiceImpl = new QrCodeServiceImpl();
			Path currentPath = Path.of(outputImageFileTF.getValue());
			try {
			System.out.println("Generate " + vcard.toString());
			qrCodeServiceImpl.generateQrCodeToFile(
					vcard.toString(), 
					300, 
					currentPath
					);
			} catch ( Exception ex) {}
			qrcodeSpan.setText(outputImageFileTF.getValue());
		});
	
	}
	
}
