package c0321g1_pawnshop_backend.service_impl.contract;

import c0321g1_pawnshop_backend.dto.contract.ContractDto;
<<<<<<< HEAD
=======
import c0321g1_pawnshop_backend.dto.contract.ContractEditDto;
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
import c0321g1_pawnshop_backend.entity.contract.Contract;
import c0321g1_pawnshop_backend.repository.contract.ContractRepository;
import c0321g1_pawnshop_backend.service.contract.ContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
<<<<<<< HEAD
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;
=======
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
<<<<<<< HEAD
    ContractRepository contractRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    /*Long*/
    @Override
    public Integer createLiquidateContract(String contractCode, String productName, Long customerId,
                                           String dateLiquidation, Long typeContractId,
                                           Long statusId, int flag, int totalMoney, int loan,
                                           int profit) {
        return contractRepository.createLiquidateContract(contractCode, productName, customerId, dateLiquidation,
                typeContractId, statusId, flag, totalMoney, loan, profit);
    }

    /*Long*/
    @Override
    public Page<Contract> findProductLiquidation(Pageable pageable) {
        return contractRepository.findProductLiquidation(pageable);
    }

    /*Long*/
    @Override
    public Contract searchContractByCode(String contractCode) {
        return contractRepository.searchContractByCode(contractCode);
    }

    /*long*/
    @Override
    public Contract getContractById(Long id) {
        return contractRepository.getContractById(id);
    }

    /*long*/
    @Override
    public Page<Contract> searchContractLiquidation(String nameProduct, String nameTypeProduct, Integer loan, Pageable pageable) {
        return contractRepository.searchContractLiquidation(nameProduct, nameTypeProduct, loan, pageable);
    }

    /*long*/
    @Override
    public Integer setStatusById(Long id, Long statusId) {
        return contractRepository.setStatusById(id, statusId);
    }

    /*long*/
    @Override
    public Integer upDateLiquidationContract(String dateLiquidation, int totalMoney, Long customerId, Long contractId) {
        return contractRepository.upDateLiquidationContract(dateLiquidation, totalMoney, customerId, contractId);
    }

    /*long*/
    @Override
    public String initCodeAuto() {
        List<Contract> contracts = contractRepository.getContractList();
        String contractCode = "";
        if (contracts.isEmpty()) {
            contractCode = "HD-0001";
        } else {
            Long code = contracts.get(contracts.size() - 1).getContractId();
            contractCode = "HD-" + String.format("%04d", ++code);
        }
        return contractCode;
    }


    //Linh code
    @Override
    public Map<String, Object> saveContract(ContractDto contractDto, BindingResult bindingResult) throws MessagingException {
        Map<String, Object> result = new HashMap<>();

        List<String> errors = new ArrayList<>();
        boolean isError = false;
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(ob ->
                    errors.add(ob.getDefaultMessage()));
            result.put("status", false);
            result.put("msg", "T???o m???i h???p ?????ng th???t b???i.");
            result.put("errors", errors);
            isError = true;
        }



        if (checkEndDate(contractDto)) {
            result.put("status", false);
            result.put("msgEndDate", "Ng??y k???t th??c ph???i l???n h??n ng??y b???t ?????u, vui l??ng ch???n l???i.");
            isError = true;
        }

        if (isError) {
            return result;
        }

        Contract contract = new Contract();
        contractDto.setProductName(contractDto.getProductName().trim());
        contractDto.setContractCode(initCode());
        BeanUtils.copyProperties(contractDto, contract);
        contractRepository.saveContract(contract.getContractCode(), contract.getStartDate(), contract.getEndDate(),
                contract.getProductName(), contract.getProductImage(), contract.getLoan(), contract.getProfit(),
                contract.getCustomer().getCustomerId(), contract.getTypeProduct().getTypeProductId(),
                1L, 1L, 1, null, 0);
        sendEmailAfterCreateContract(contract);
        result.put("status", true);
        result.put("msg", "Th??m m???i h???p ?????ng th??nh c??ng.");
        return result;
    }

    private String initCode() {
        List<Contract> contracts = contractRepository.getContractList();
        String contractCode = "";
        if (contracts.isEmpty()) {
            contractCode = "HD-0001";
        } else {
            Long code = contracts.get(contracts.size() - 1).getContractId();
            contractCode = "HD-" + String.format("%04d", ++code);
        }
        return contractCode;
    }



=======
    private ContractRepository contractRepository;
    @Autowired
    private JavaMailSender javaMailSender;


    private boolean checkStartDate(ContractDto contractDto) {
        int timeDiff = contractDto.getStartDate().compareTo(LocalDate.now().toString());
        return timeDiff < 0;
    }

>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
    private boolean checkEndDate(ContractDto contractDto) {
        int timeDiff = contractDto.getStartDate().compareTo(contractDto.getEndDate());
        return timeDiff >= 0;
    }

<<<<<<< HEAD
    //Linh code
    private void sendEmailAfterCreateContract(Contract contract) throws MessagingException {

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(contract.getCustomer().getEmail());
//        message.setSubject("[Th??ng b??o] T???o m???i h???p ?????ng c???m ????? th??nh c??ng");
//        message.setText("Ch??o, kh??ch h??ng " + contract.getCustomer().getName() + "\n" +
//                "Ch??c m???ng b???n ???? t???o m???i h???p ?????ng c???m ????? th??nh c??ng. \n" +
//                "Th??ng tin h???p ?????ng: \n" +
//                "M?? h???p ?????ng: " + contract.getContractCode() + ".\n" +
//                "Ng??y b???t ?????u: " + contract.getStartDate() + ".\n" +
//                "Ng??y k???t th??c: " + contract.getEndDate() + ".\n" +
//                "Ti???n cho vay: " + contract.getLoan()  + ".\n" +
//                "Ti???n l??i: " + contract.getProfit() + ".\n" +
//                "\n" +
//                "Thanks and Best Regards!\n" +
//                "M: 1800-6969\n" +
//                "E: codegym.c0321g1@gmail.com");
//
//        javaMailSender.send(message);


        final String fromEmail = "codegym.c0321g1@gmail.com";
        // Mat khai email cua ban
        final String password = "Qwerty@123";
        // dia chi email nguoi nhan
        final String toEmail = "hauhpdn2015@gmail.com";
        final String subject = "[Th??ng b??o] T???o m???i h???p ?????ng c???m ????? th??nh c??ng";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        message.setSubject(subject);
        String htmlContent = "<div style=\"align-content: center; text-align: center\">" +
                "<img src=\"https://firebasestorage.googleapis.com/v0/b/c0321g1-sprint1.appspot.com/o/RoomsImages%2F1634479388976?alt=media&token=07afab21-e1f6-487e-8975-e275f52f40e5\">\n" +
                "<h3>C??NG TY TNHH MTV C???M ????? C03</h3>\n" +
                "<h1 style=\"color: red\">TH??NG B??O X??C NH???N H???P ?????NG C???M ????? </h1>" +
                "<p>Ch??o, kh??ch h??ng " + contract.getCustomer().getName() +
                " Ch??c m???ng b???n ???? t???o m???i h???p ?????ng c???m ????? th??nh c??ng.</p>" +

                                "<p>Th??ng tin h???p ?????ng: </p>" +
                "<p>M?? h???p ?????ng: " + contract.getContractCode() + "</p>" +
                "<p>Ng??y b???t ?????u: " + contract.getStartDate() + "</p>" +
                "<p>Ng??y k???t th??c: " + contract.getEndDate() + "</p>" +
                "<p style=\"color:red\">Ti???n cho vay: " + contract.getLoan()  + " VND</p>" +
                "<p style=\"color:red\">Ti???n l??i: " + contract.getProfit() + " VND</p>"+
        "</div>" +
               "<div style=\"background-color: green\">\n" +
                "  <div  style=\"text-align: center; color: white; padding-right: 2px; padding-left: 2px\">\n" +
                "    <p>C???m ??n qu?? kh??ch ???? s??? d???ng d???ch v???</p>\n" +
                "    <p>D???ch v??? c???m ????? ???? N???ng uy t??n - MST: 696 696 696</p>\n" +
                "    <p>295 Nguy???n T???t Th??nh, Thanh B??nh, H???i Ch??u, ???? N???ng 550000, Vi???t Nam\n" +
                "    </p>\n" +
                "  </div>\n" +
                "</div>";
        message.setContent(htmlContent, "text/html; charset=utf-8");
        Transport.send(message);
        System.out.println("Gui mail thanh cong");



    }


    //    creator: vinhdn. Get list contract
    @Override
    public Page<Contract> getListContract(Pageable pageable) {
        return contractRepository.getListContract(pageable, 1);
    }

    //    creator: vinhdn. search contract
    @Override
    public Page<Contract> searchContract(Pageable pageable, String contractCode, String customerName,
                                         String productName, String startDate) {
        return contractRepository.searchContract(pageable, contractCode,
                customerName, productName, startDate, 1);
    }

    //    creator: vinhdn. payment contract
    @Override
    public void paymentContract(Contract contract) {
        contractRepository.paymentContract(2, contract.getTotalMoney(), contract.getContractId());
        Optional<Contract> contract1 = contractRepository.findById(contract.getContractId());
        contract1.ifPresent(this::sendPaymentEmail);
    }

    /*Vu*/
    @Override
    public Page<Contract> getListContractHistory(Pageable pageable) {
        return contractRepository.getListContractHistory(pageable);
    }

    //    creator: vinhdn. send mail contract
    public String sendPaymentEmail(Contract contract) {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(contract.getCustomer().getEmail());
        message.setSubject("H???p ?????ng c???m ????? c???a qu?? kh??ch ???? ???????c thanh to??n");
        message.setText("Dear " + contract.getCustomer().getName() + "\n" +
                "H???p ?????ng c???m ????? c???a qu?? kh??ch ???? ???????c thanh to??n" + "\n" +
                "H???p ?????ng id s??? " + contract.getContractId() + "\n" +
                "????? c???m l?? " + contract.getProductName() + "\n" +
                "S??? ti???n thanh to??n l?? " + contract.getTotalMoney() + "VND");

        // Send Message!
        javaMailSender.send(message);
        return "Email Sent!";
    }


=======
    @Override
    public Page<Contract> getListContract(Pageable pageable) {
        return this.contractRepository.getListContract(pageable);
    }

>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
    @Override
    public void deleteContract(Long id) {
        this.contractRepository.deleteContract(id);
    }

    @Override
<<<<<<< HEAD
    public Page<Contract> searchContractHistory(Pageable pageable, String customer, String productName, String statusContract, String typeContract, String startDateFrom, String startDateTo) {
        return this.contractRepository.searchContractHistory( customer ,  productName ,  statusContract,  typeContract , startDateFrom, startDateTo, pageable);
    }

    @Override
    public List<Contract> searchTNameContract(String customer, String statusContract) {
        return this.contractRepository.searchTenContract(customer, statusContract);
=======
    public Page<Contract> searchContract(Pageable pageable, String customer, String productName, String statusContract, String typeContract, String startDateFrom, String startDateTo) {
        return this.contractRepository.searchContract('%' + customer + '%','%' + productName + '%', '%' + statusContract + '%','%' + typeContract + '%',  startDateFrom , startDateTo ,pageable);
    }

    @Override
    public List<Contract> searchTNameContract( String customer, String statusContract) {
        return this.contractRepository.searchTenContract( customer, statusContract);
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
    }

    @Override
    public Contract findByIdContract(Long contractId) {
        return this.contractRepository.findByContractId(contractId);
    }

    @Override
    public void updateContractDto(Contract contract) {

    }

    @Override
    public void save(Contract contract1) {
        this.contractRepository.save(contract1);
    }

<<<<<<< HEAD
=======
    private void sendSimpleEmail(Contract contract) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(contract.getCustomer().getEmail());
        message.setSubject("[Th??ng b??o] H???p ?????ng C???a B???n S???p H???t H???n");
        message.setText("Ch??o, kh??ch h??ng " + contract.getCustomer().getName() + "\n"
                + "H???p ?????ng c???a b???n s???p h???t h???n, m?? h???p ?????ng l?? " + contract.getContractCode() + ".");

        javaMailSender.send(message);
    }

>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
    @Override
    public List<Contract> page10Contract() {
        return contractRepository.page10Contract();
    }

    //vu code
    @Override
    public List<String> getAllEmailToSend() {
        return contractRepository.getAllEmailToSend();
    }

<<<<<<< HEAD

=======
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
}
