package com.github.xiejinhong.jooq.codegen;

import lombok.Getter;
import lombok.Setter;
import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;
import org.jooq.meta.TableDefinition;
import org.jooq.tools.StringUtils;

/**
 * The type CustomGeneratorStrategy.
 *
 * @author Miracle.XJH
 * @date 2019年04月22日 21时11分56秒
 */
@Setter
@Getter
public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {
    /**
     * The Pojo suffix.
     */
    private String pojoSuffix = "Entity";
    /**
     * The Table prefix.
     */
    private String tablePrefix = "T";
    /**
     * The Dao suffix.
     */
    private String daoSuffix = "Repository";

    /**
     * Gets java class name.
     *
     * @param definition the definition
     * @param mode       the mode
     * @return the java class name
     */
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        switch (mode) {
            case POJO:
                return super.getJavaClassName(definition, mode) + getPojoSuffix();
            case RECORD:
                return super.getJavaClassName(definition, mode);
            case DAO:
                String s = StringUtils.toCamelCase(
                        definition.getOutputName()
                                .replace(' ', '_')
                                .replace('-', '_')
                                .replace('.', '_')
                );
                return s + getDaoSuffix();
            case ENUM:
            case DOMAIN:
            case INTERFACE:
            case DEFAULT:
                if (definition instanceof TableDefinition) {
                    return getTablePrefix() + super.getJavaClassName(definition, mode);
                }
            default:
                return super.getJavaClassName(definition, mode);
        }
    }

}