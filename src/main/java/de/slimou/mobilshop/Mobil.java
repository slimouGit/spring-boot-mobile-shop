package de.slimou.mobilshop;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "mobil")
public class Mobil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "desrcription", nullable = false)
    private String desrcription;

    @Column(name = "price", nullable = false)
    private Double price;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    public Mobil() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mobil mobil = (Mobil) o;

        if (name != null ? !name.equals(mobil.name) : mobil.name != null) return false;
        if (desrcription != null ? !desrcription.equals(mobil.desrcription) : mobil.desrcription != null) return false;
        if (price != null ? !price.equals(mobil.price) : mobil.price != null) return false;
        return Arrays.equals(image, mobil.image);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (desrcription != null ? desrcription.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Mobil{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desrcription='" + desrcription + '\'' +
                ", price=" + price +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesrcription() {
        return desrcription;
    }

    public void setDesrcription(String desrcription) {
        this.desrcription = desrcription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
