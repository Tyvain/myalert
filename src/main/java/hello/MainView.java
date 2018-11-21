package hello;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route
public class MainView extends VerticalLayout {

//	private final CustomerRepository repo;
//
//	private final CustomerEditor editor;
//
//	final Grid<Customer> grid;
//
//	final TextField filter;
//
//	private final Button addNewBtn;

	public MainView(CustomerRepository repo, CustomerEditor editor) {
//		this.repo = repo;
//		this.editor = editor;
//		this.grid = new Grid<>(Customer.class);
//		this.filter = new TextField();
//		this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());
//
//		// build layout
//		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
//		add(actions, grid, editor);
//
//		grid.setHeight("300px");
//		grid.setColumns("id", "firstName", "lastName");
//		grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
//
//		filter.setPlaceholder("Filter by last name");
//
//		// Hook logic to components
//
//		// Replace listing with filtered content when user changes filter
//		filter.setValueChangeMode(ValueChangeMode.EAGER);
//		filter.addValueChangeListener(e -> listCustomers(e.getValue()));
//
//		// Connect selected Customer to editor or hide if none is selected
//		grid.asSingleSelect().addValueChangeListener(e -> {
//			editor.editCustomer(e.getValue());
//		});
//
//		// Instantiate and edit new Customer the new button is clicked
//		addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));
//
//		// Listen changes made by the editor, refresh data from backend
//		editor.setChangeHandler(() -> {
//			editor.setVisible(false);
//			listCustomers(filter.getValue());
//		});
//
//		// Initialize listing
//		listCustomers(null);

		MemoryBuffer buffer = new MemoryBuffer();
		Upload upload = new Upload(buffer);
		upload.setId("i18n-upload");

		upload.addSucceededListener(event -> System.out.println("Done!"));

		UploadI18N i18n = new UploadI18N();
		i18n.setDropFiles(
				new UploadI18N.DropFiles().setOne("Перетащите файл сюда...")
						.setMany("Перетащите файлы сюда..."))
				.setAddFiles(new UploadI18N.AddFiles()
						.setOne("Выбрать файл").setMany("Добавить файлы"))
				.setCancel("Отменить")
				.setError(new UploadI18N.Error()
						.setTooManyFiles("Слишком много файлов.")
						.setFileIsTooBig("Слишком большой файл.")
						.setIncorrectFileType("Некорректный тип файла."))
				.setUploading(new UploadI18N.Uploading()
						.setStatus(new UploadI18N.Uploading.Status()
								.setConnecting("Соединение...")
								.setStalled("Загрузка застопорилась.")
								.setProcessing("Обработка файла..."))
						.setRemainingTime(
								new UploadI18N.Uploading.RemainingTime()
										.setPrefix("оставшееся время: ")
										.setUnknown(
												"оставшееся время неизвестно"))
						.setError(new UploadI18N.Uploading.Error()
								.setServerUnavailable("Сервер недоступен")
								.setUnexpectedServerError(
										"Неожиданная ошибка сервера")
								.setForbidden("Загрузка запрещена")))
				.setUnits(Stream
						.of("Б", "Кбайт", "Мбайт", "Гбайт", "Тбайт", "Пбайт",
								"Эбайт", "Збайт", "Ибайт")
						.collect(Collectors.toList()));

		upload.setI18n(i18n);
		add(upload);
	}

//	// tag::listCustomers[]
//	void listCustomers(String filterText) {
//		if (StringUtils.isEmpty(filterText)) {
//			grid.setItems(repo.findAll());
//		}
//		else {
//			grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
//		}
//	}
//	// end::listCustomers[]

}
