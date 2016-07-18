package upwork.enums;

/**
 * Created with Intellij IDEA
 * User: filosof_77
 * Date: 15.07.16.
 * Time: 18:47
 */
public enum Sorting {
    JOBS_ANY(""),
    JOBS_90_UP("//a[@data-facet-html=\"90% job success & up\"]"),
    JOBS_80_UP("");

    public final String xpath;

    Sorting(String xpath) {
        this.xpath = xpath;
    }
}
