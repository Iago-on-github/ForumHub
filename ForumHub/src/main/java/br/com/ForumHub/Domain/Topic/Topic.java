package br.com.ForumHub.Domain.Topic;

import br.com.ForumHub.Domain.User.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @Enumerated(EnumType.STRING)
    private topicStatus topicStatus = br.com.ForumHub.Domain.Topic.topicStatus.ATIVO;
    @ManyToOne
    private User author;
    private String Course;

    public Topic(DataRegistrationTopic data) {
        this(data.id(), data.title(), data.message(), data.topicStatus(), data.author(), data.course());
    }

    public void updateInfos(DataUpdateInfos data) {
        if (data.title() != null) {
            this.title = data.title();
        }
        if (data.message() != null) {
            this.message = data.message();
        }
    }
}
