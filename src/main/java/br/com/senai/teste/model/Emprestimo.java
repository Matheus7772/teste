package br.com.senai.teste.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "emprestimo")
public class Emprestimo {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataPrevistaDevolucao;
    private static final BigDecimal VALOR_MULTA_DIARIA = new BigDecimal("2.00");

    @ManyToOne 
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;
    
    @ManyToOne 
    @JoinColumn(name = "livro_id", nullable = false)
    private Livros livro;

    public Emprestimo() {
    }


    public int getId() {
        return id;
    }


    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }


    public Livros getLivro() {
        return livro;
    }


    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }


    public void setLivro(Livros livro) {
        this.livro = livro;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Aluno getAluno() {
        return aluno;
    }


    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }


    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }


    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }


    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }


    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    
    public String getSituacao(){
        if(dataDevolucao != null){
            return "Devolvido";
        } 
        if(dataPrevistaDevolucao == null){
            return "Sem Previsão de Devolução";
        } 
        if(dataPrevistaDevolucao.isBefore(LocalDate.now())){ 
            return "ATRASADO";
        }
        return "ATIVO";
    }

    public long getDiasAtraso(){

        if(dataPrevistaDevolucao == null){
            return 0;
        }
        LocalDate dataFinal;
        if (dataDevolucao == null) {
            dataFinal = LocalDate.now();   
        }
        else {
            dataFinal = dataDevolucao;
        }
        if (!dataFinal.isAfter(dataPrevistaDevolucao)) {
            return 0; 
            
        }

        return ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataFinal);
    }

    public BigDecimal getValorMulta(){
        long diasAtraso = getDiasAtraso();
        if(diasAtraso <= 0){
            return BigDecimal.ZERO;
        }
        return VALOR_MULTA_DIARIA.multiply(BigDecimal.valueOf(diasAtraso));
    }
    
 }
