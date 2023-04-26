# Adapter Design Pattern

    - STRUCTURAL PATTERN
    
    - acts as a bridge between 2 incompatible interfaces
    - EX: card reader which acts as an adapter between a memory card and a laptop.
    
    

    AdapterPatternDemo
        +main():void
        
        (uses) 
        
    
    AudioPlayer (implements Media Player) 
        -mediaAdapter:MediaAdapter
        +play():void
        
        
                                    MediaPlayer (Interface) 
                                        +play():void
        (Uses) 
        
        
    MediaAdapter (implements MediaPlayer) 
        -advancedMediaPlayer:AdvancedMediaPlayer
        +MediaAdapter():void
        +play():void
        
        (uses)                      (uses) 
        
    VlcPlayer                       Mp4Player
        +playVlc():void                 +playVlc():void
        +playMp4():void                 +playMp4():void 
        
    
    AdvancedMediaPlayer(interface)            
        +playVlc():void
        +playMp4():void                         
    