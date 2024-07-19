package G26.Project.Model.Search;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Represents an attribute constraint with an operator and a numeric value.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class AttributeConstraint {
    private final String operator;
    private final double value;
    /**
     * Constructs a new AttributeConstraint with the specified operator and value.
     *
     * @param operator The operator (e.g., "<", ">", "==") of the constraint.
     * @param value    The numeric value of the constraint.
     */
    public AttributeConstraint(String operator, double value) {
        this.operator = operator;
        this.value = value;
    }
    public String getOperator() {
        return operator;
    }
    public double getValue() {
        return value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeConstraint that = (AttributeConstraint) o;
        if (Double.compare(that.value, value) != 0) return false;
        return Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = operator != null ? operator.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "AttributeConstraint{" +
                "operator='" + operator + '\'' +
                ", value=" + value +
                '}';
    }
}
