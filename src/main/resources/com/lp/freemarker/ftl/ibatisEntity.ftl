package ${packagePath};

import lombok.Data;
<#list importList?if_exists as importPackage>  
import ${importPackage.importPackage};
</#list>
/**
* ${entityExplain}.
*/
@Data
public class ${className} {

<#list entityFieldList?if_exists as field>
    /**
     * ${(field.fieldExplain)!}
     */
    private ${field.fieldType} ${field.fieldName};

</#list>
}
