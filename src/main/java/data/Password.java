package data;

public final class Password {

    private final String password;

    public Password(String password) {
        if (password == null)
            throw new NullPointerException("Password shouldn't reference to null");
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password other = (Password) o;
        return password.equals(other.password);
    }

    @Override
    public String toString() {
        return "Password{" + "password='" + password + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }
}
