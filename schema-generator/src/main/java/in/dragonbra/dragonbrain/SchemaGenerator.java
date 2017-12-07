package in.dragonbra.dragonbrain;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SchemaGenerator {

    private static final Logger logger = LogManager.getLogger(SchemaGenerator.class);

    public static void main(String[] args) throws Exception {
        String packageName[] = {"in.dragonbra.dragonbrain.entity"};
        SchemaGenerator gen = new SchemaGenerator();
        gen.generate(packageName);
    }

    private SchemaGenerator() {
    }

    private void generate(String[] packagesName) throws Exception {

        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .build());

        for (String packageName : packagesName) {
            logger.info("packageName: " + packageName);
            for (Class clazz : getClasses(packageName)) {
                logger.info("Class: " + clazz);
                metadata.addAnnotatedClass(clazz);
            }
        }

        SchemaExport export = new SchemaExport((MetadataImplementor) metadata.buildMetadata());

        export.setDelimiter(";");
        export.setFormat(true);
        export.execute(true, false, false, false);
    }

    private List<Class> getClasses(String packageName) throws Exception {
        File directory;
        try {
            ClassLoader cld = getClassLoader();
            URL resource = getResource(packageName, cld);
            directory = new File(resource.getFile());
        } catch (NullPointerException ex) {
            throw new ClassNotFoundException(packageName + " does not appear to be a valid package");
        }
        return collectClasses(packageName, directory);
    }

    private ClassLoader getClassLoader() throws ClassNotFoundException {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new ClassNotFoundException("Can't get class loader.");
        }
        return cld;
    }

    private URL getResource(String packageName, ClassLoader cld) throws ClassNotFoundException {
        String path = packageName.replace('.', '/');
        URL resource = cld.getResource(path);
        if (resource == null) {
            throw new ClassNotFoundException("No resource for " + path);
        }
        return resource;
    }

    private List<Class> collectClasses(String packageName, File directory) throws ClassNotFoundException {
        if (directory.exists()) {
            String[] files = directory.list();
            if (files != null) {
                return Arrays.stream(files)
                        .filter(file -> file.endsWith(".class"))
                        .map(file -> loadClass(packageName, file))
                        .collect(Collectors.toList());
            }
        } else {
            throw new ClassNotFoundException(packageName + " is not a valid package");
        }
        return new ArrayList<>();
    }

    private Class loadClass(String packageName, String file) {
        try {
            return Class.forName(packageName + '.' + file.substring(0, file.length() - 6));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException();
        }
    }
}
