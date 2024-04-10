package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 *
 */
public class Application {

    private static JpaService jpaService = JpaService.getInstance();

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter Programming Language: ");
            String pl = input.nextLine();
            System.out.println("\nEnter rating: ");
            Integer rating = input.nextInt();


            EntityManagerFactory entityManagerFactory = jpaService.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(new ProgrammingLanguage(pl, rating));

            createProgrammingLanguages();
            printProgrammingLanguages();

        } finally {
            jpaService.shutdown();
        }

    }

    private static void createProgrammingLanguages() {
        jpaService.runInTransaction(entityManager -> {
            Arrays.stream("Java, JavaScript, TypeScript, C, C++, C#, Python, Rust, .Net, PHP".split(","))
                    .map(name -> new ProgrammingLanguage(name, (int) (Math.random() * 10 )))
                    .forEach(entityManager::persist);
            return null;
        });
    }

    private static void printProgrammingLanguages() {
        List<ProgrammingLanguage> list = jpaService.runInTransaction(entityManager -> {
             return entityManager.createQuery(
                    "select pl from ProgrammingLanguage pl",
                    ProgrammingLanguage.class
            ).getResultList();
        });

        list.stream()
                .map(pl -> pl.getName() + ": " + pl.getRating())
                .forEach(System.out::println);
    }
}
