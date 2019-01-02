# id 311250484
# user gottesa

compile: copy
	javac -d bin -cp src/biuoop-1.4.jar src/Ass7Game.java src/anime/AnimationRunner.java src/anime/CountdownAnimation.java src/anime/EndScreen.java src/anime/HighScoresAnimation.java src/anime/KeyPressStoppableAnimation.java src/anime/LevelIndicator.java src/anime/LivesIndicator.java src/anime/MenuAnimation.java src/anime/PauseScreen.java src/anime/ScoreIndicator.java src/enemies/Alien.java src/enemies/AlienCol.java src/enemies/AliensRow.java src/gameobjects/Ball.java src/gameobjects/BallRemover.java src/gameobjects/Block.java src/gameobjects/BlockRemover.java src/gameobjects/CollisionInfo.java src/gameobjects/Counter.java src/gameobjects/GameEnvironment.java src/gameobjects/GameFlow.java src/gameobjects/GameLevel.java src/gameobjects/MenuSelection.java src/gameobjects/Paddle.java src/gameobjects/ScoreTrackingListener.java src/gameobjects/SpriteCollection.java src/gameobjects/Velocity.java src/geometry/ColoredRectangle.java src/geometry/Line.java src/geometry/Point.java src/geometry/Rectangle.java src/interfaces/Animation.java src/interfaces/Collidable.java src/interfaces/HitListener.java src/interfaces/HitNotifier.java src/interfaces/LevelInformation.java src/interfaces/Menu.java src/interfaces/Sprite.java src/interfaces/Task.java src/level/Level.java src/saves/HighScoresTable.java src/saves/ScoreInfo.java src/saves/SortByScore.java

run:
	java -cp src/biuoop-1.4.jar:bin:resources:. Ass7Game
	
jar:
	jar cfm space-invaders.jar Manifest.mf -C bin .
	
bin:
	mkdir bin
	
copy: bin
	cp -R src/resources bin/
	